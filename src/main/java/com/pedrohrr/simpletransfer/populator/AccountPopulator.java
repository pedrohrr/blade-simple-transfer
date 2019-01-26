package com.pedrohrr.simpletransfer.populator;

import com.blade.ioc.annotation.Bean;
import com.pedrohrr.simpletransfer.data.account.AccountCreate;
import com.pedrohrr.simpletransfer.data.account.AccountDetailed;
import com.pedrohrr.simpletransfer.data.account.AccountMinimal;
import com.pedrohrr.simpletransfer.exception.SimpleTransferException;
import com.pedrohrr.simpletransfer.model.Account;

@Bean
public class AccountPopulator extends ServicePopulator {

    public AccountMinimal toMinimal(final Account model) throws SimpleTransferException {
        final AccountMinimal data = new AccountMinimal();
        data.setIban(model.getIban());
        data.setClient(getClientMinimal(model.getClient()));
        return data;
    }

    public AccountDetailed toDetailed(final Account model) throws SimpleTransferException {
        final AccountDetailed data = new AccountDetailed();
        data.setId(model.getId());
        data.setClient(getClientMinimal(model.getClient()));
        data.setBalance(model.getBalance());
        data.setCurrency(model.getCurrency());
        data.setIban(model.getIban());
        data.setStatus(model.getStatus());
        return data;
    }

    public Account fromCreate(final AccountCreate data) {
        final Account model = new Account();
        model.setIban(data.getIban());
        model.setClient(data.getClient());
        model.setCurrency(data.getCurrency());
        return model;
    }

}
