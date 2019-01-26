package com.pedrohrr.simpletransfer.populator;

import com.blade.ioc.annotation.Bean;
import com.pedrohrr.simpletransfer.data.transfer.TransferCreate;
import com.pedrohrr.simpletransfer.data.transfer.TransferDetailed;
import com.pedrohrr.simpletransfer.data.transfer.TransferMinimal;
import com.pedrohrr.simpletransfer.exception.SimpleTransferException;
import com.pedrohrr.simpletransfer.model.Transfer;

@Bean
public class TransferPopulator extends ServicePopulator {

    public TransferMinimal toMinimal(final Transfer model) throws SimpleTransferException {
        return null;
    }

    public TransferDetailed toDetailed(final Transfer model) throws SimpleTransferException {
        return null;
    }

    public Transfer fromCreate(final TransferCreate data) {
        return null;
    }

}
