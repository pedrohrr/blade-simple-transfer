package com.pedrohrr.simpletransfer.populator;

import com.blade.ioc.annotation.Bean;
import com.blade.ioc.annotation.Inject;
import com.pedrohrr.simpletransfer.data.transfer.TransferCreate;
import com.pedrohrr.simpletransfer.data.transfer.TransferDetailed;
import com.pedrohrr.simpletransfer.data.transfer.TransferMinimal;
import com.pedrohrr.simpletransfer.exception.SimpleTransferException;
import com.pedrohrr.simpletransfer.model.Transfer;

@Bean
public class TransferPopulator {

    @Inject
    private ServicePopulator servicePopulator;

    public TransferMinimal toMinimal(final Transfer model) throws SimpleTransferException {
        final TransferMinimal data = new TransferMinimal();
        data.setSender(servicePopulator.getAccountMinimal(model.getSender()));
        data.setReceiver(servicePopulator.getAccountMinimal(model.getReceiver()));
        data.setAmount(model.getAmount());
        data.setStatus(model.getStatus());
        return data;
    }

    public TransferDetailed toDetailed(final Transfer model) throws SimpleTransferException {
        final TransferDetailed data = new TransferDetailed();
        data.setId(model.getId());
        data.setSender(servicePopulator.getAccountMinimal(model.getSender()));
        data.setReceiver(servicePopulator.getAccountMinimal(model.getReceiver()));
        data.setAmount(model.getAmount());
        data.setStatus(model.getStatus());
        data.setConversion(model.getConversion());
        data.setCreatedAt(model.getCreatedAt());
        data.setProcessedAt(model.getProcessedAt());
        data.setNotes(model.getNotes());
        return data;
    }

    public Transfer fromCreate(final TransferCreate data) {
        final Transfer model = new Transfer();
        model.setSender(data.getSender());
        model.setReceiver(data.getReceiver());
        model.setAmount(data.getAmount());
        return model;
    }

}
