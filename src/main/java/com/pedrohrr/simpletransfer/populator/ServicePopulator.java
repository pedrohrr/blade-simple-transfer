package com.pedrohrr.simpletransfer.populator;

import com.blade.ioc.annotation.Bean;
import com.blade.ioc.annotation.Inject;
import com.pedrohrr.simpletransfer.data.account.AccountMinimal;
import com.pedrohrr.simpletransfer.data.client.ClientMinimal;
import com.pedrohrr.simpletransfer.exception.SimpleTransferException;
import com.pedrohrr.simpletransfer.model.Account;
import com.pedrohrr.simpletransfer.model.Client;
import com.pedrohrr.simpletransfer.service.AccountService;
import com.pedrohrr.simpletransfer.service.ClientService;

@Bean
public class ServicePopulator {

    @Inject
    private AccountService accountService;

    @Inject
    private ClientService clientService;

    @Inject
    private AccountPopulator accountPopulator;

    @Inject
    private ClientPopulator clientPopulator;


    ClientMinimal getClientMinimal(final Long id) throws SimpleTransferException {
        final Client client = clientService.findById(id);
        return clientPopulator.toMinimal(client);
    }

    AccountMinimal getAccountMinimal(final Long id) throws SimpleTransferException {
        final Account account = accountService.findById(id);
        return accountPopulator.toMinimal(account);
    }

}
