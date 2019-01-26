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
import com.pedrohrr.simpletransfer.model.Transfer;
import com.pedrohrr.simpletransfer.populator.TransferPopulator;
import com.pedrohrr.simpletransfer.service.TransferService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.ArrayList;
import java.util.List;

@Bean
public class TransferFacade {

    private static final Logger LOG = LoggerFactory.getLogger(TransferFacade.class);

    private static final String NO_TRANSFERS_FOUND = "No transfers found";
    private static final String TRANSFER = "Transfer";
    private static final String NO_POSTED_TRANSFERS_FOUND = "No Posted transfers found for processing";

    @Inject
    private TransferService service;

    @Inject
    private TransferPopulator populator;

    @Inject
    private Validator validator;

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

    public List<TransferMinimal> findByClientId(final Long id) throws SimpleTransferException {
        final List<TransferMinimal> transfers = new ArrayList<>();

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

        } catch (NotFoundException e) {
            LOG.warn(NO_POSTED_TRANSFERS_FOUND, e);
        }
    }

    private List<Transfer> getByAccount(Long id) throws NotFoundException {
        final List<Transfer> transfers = new ArrayList<>();

        try {
            transfers.addAll(service.findByReceiverId(id));
        } catch (NotFoundException e) {
            LOG.debug(NO_TRANSFERS_FOUND, e);
        }

        try {
            transfers.addAll(service.findBySenderId(id));
        } catch (NotFoundException e) {
            LOG.debug(NO_TRANSFERS_FOUND, e);
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
            LOG.debug(NO_TRANSFERS_FOUND, e);
        }

        try {
            transfers.addAll(service.findBySenderClientId(id));
        } catch (NotFoundException e) {
            LOG.debug(NO_TRANSFERS_FOUND, e);
        }

        if (transfers.isEmpty()) {
            throw new NotFoundException(TRANSFER);
        }

        return transfers;
    }

}
