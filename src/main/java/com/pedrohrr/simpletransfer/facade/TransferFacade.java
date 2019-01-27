package com.pedrohrr.simpletransfer.facade;

import com.blade.ioc.annotation.Bean;
import com.blade.ioc.annotation.Inject;
import com.pedrohrr.simpletransfer.data.Validator;
import com.pedrohrr.simpletransfer.data.transfer.TransferCreate;
import com.pedrohrr.simpletransfer.data.transfer.TransferDetailed;
import com.pedrohrr.simpletransfer.data.transfer.TransferMinimal;
import com.pedrohrr.simpletransfer.enumeration.TransferStatus;
import com.pedrohrr.simpletransfer.exception.NotFoundException;
import com.pedrohrr.simpletransfer.exception.SimpleTransferException;
import com.pedrohrr.simpletransfer.model.Account;
import com.pedrohrr.simpletransfer.model.Transfer;
import com.pedrohrr.simpletransfer.populator.TransferPopulator;
import com.pedrohrr.simpletransfer.service.AccountService;
import com.pedrohrr.simpletransfer.service.CurrencyService;
import com.pedrohrr.simpletransfer.service.TransferService;
import io.github.biezhi.anima.Anima;
import lombok.extern.slf4j.Slf4j;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.function.Consumer;

@Bean
@Slf4j
public class TransferFacade {

    private static final String NO_TRANSFERS_FOUND = "No transfers found";
    private static final String TRANSFER = "Transfer";
    private static final String NO_POSTED_TRANSFERS_FOUND = "No Posted transfers found for processing";
    private static final String UNABLE_TO_PROCESS_TRANSFER = "Unable to process Transfer";
    private static final String UNABLE_TO_UPDATE_TRANSFER = "Unable to update transfer";

    @Inject
    private TransferService service;

    @Inject
    private AccountService accountService;

    @Inject
    private TransferPopulator populator;

    @Inject
    private Validator validator;

    @Inject
    private CurrencyService currencyService;

    public TransferDetailed findById(final Long id) throws SimpleTransferException {
        return populator.toDetailed(service.findById(id));
    }

    public List<TransferMinimal> findByAccountId(final Long id) throws SimpleTransferException {
        final List<TransferMinimal> transfers = new ArrayList<>();

        for (final Transfer t : getByAccount(id)) {
            transfers.add(populator.toMinimal(t));
        }

        return transfers;
    }

    public Set<TransferMinimal> findByClientId(final Long id) throws SimpleTransferException {
        final Set<TransferMinimal> transfers = new HashSet<>();

        for (final Transfer t : getByClient(id)) {
            transfers.add(populator.toMinimal(t));
        }

        return transfers;
    }

    public Long create(final TransferCreate transfer) throws SimpleTransferException {
        validator.validate(transfer);
        return service.create(populator.fromCreate(transfer));
    }

    public void process() {
        try {
            final List<Transfer> postedTransfers = service.findByStatus(TransferStatus.POSTED);
            postedTransfers.forEach(this::processTransfer);
        } catch (SimpleTransferException e) {
            log.warn(NO_POSTED_TRANSFERS_FOUND);
        }
    }

    private void processTransfer(final Transfer t) {
        Anima.atomic(() -> {
            try {
                final Account sender = accountService.findById(t.getSender());
                final Account receiver = accountService.findById(t.getSender());
                final BigDecimal rate = currencyService.getConversionRate(sender.getCurrency(), receiver.getCurrency());
                accountService.adjustBalance(t.getSender(), t.getAmount().negate());
                accountService.adjustBalance(t.getReceiver(), t.getAmount().multiply(rate));
                t.setConversion(rate);
                t.setStatus(TransferStatus.COMPLETED);
                service.update(t);
            } catch (SimpleTransferException e) {
                log.error(UNABLE_TO_PROCESS_TRANSFER, e);
                throw new RuntimeException(e.getMessage());
            }
        }).catchException(handleException(t));
    }

    private Consumer<Exception> handleException(Transfer t) {
        return e -> {
            t.setStatus(TransferStatus.CANCELED);
            t.setNotes(e.getMessage());
            try {
                service.update(t);
            } catch (NotFoundException e1) {
                log.error(UNABLE_TO_UPDATE_TRANSFER, e);
            }
        };
    }

    private List<Transfer> getByAccount(Long id) throws NotFoundException {
        final List<Transfer> transfers = new ArrayList<>();

        try {
            transfers.addAll(service.findByReceiverId(id));
        } catch (NotFoundException e) {
            log.debug(NO_TRANSFERS_FOUND, e);
        }

        try {
            transfers.addAll(service.findBySenderId(id));
        } catch (NotFoundException e) {
            log.debug(NO_TRANSFERS_FOUND, e);
        }

        if (transfers.isEmpty()) {
            throw new NotFoundException(TRANSFER);
        }

        return transfers;
    }

    private List<Transfer> getByClient(Long id) throws NotFoundException {
        final List<Transfer> transfers = new ArrayList<>();

        try {
            transfers.addAll(service.findByReceiverClientId(id));
        } catch (NotFoundException e) {
            log.debug(NO_TRANSFERS_FOUND, e);
        }

        try {
            transfers.addAll(service.findBySenderClientId(id));
        } catch (NotFoundException e) {
            log.debug(NO_TRANSFERS_FOUND, e);
        }

        if (transfers.isEmpty()) {
            throw new NotFoundException(TRANSFER);
        }

        return transfers;
    }

}
