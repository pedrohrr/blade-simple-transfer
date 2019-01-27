package com.pedrohrr.simpletransfer.facade;

import com.blade.ioc.annotation.Bean;
import com.blade.ioc.annotation.Inject;
import com.pedrohrr.simpletransfer.data.Validator;
import com.pedrohrr.simpletransfer.data.account.AccountCreate;
import com.pedrohrr.simpletransfer.data.account.AccountDeposit;
import com.pedrohrr.simpletransfer.data.account.AccountDetailed;
import com.pedrohrr.simpletransfer.data.account.AccountMinimal;
import com.pedrohrr.simpletransfer.exception.SimpleTransferException;
import com.pedrohrr.simpletransfer.model.Account;
import com.pedrohrr.simpletransfer.populator.AccountPopulator;
import com.pedrohrr.simpletransfer.service.AccountService;
import com.pedrohrr.simpletransfer.service.CurrencyService;

import java.util.ArrayList;
import java.util.List;

@Bean
public class AccountFacade {

    @Inject
    private AccountService service;

    @Inject
    private AccountPopulator populator;

    @Inject
    private Validator validator;

    @Inject
    private CurrencyService currencyService;

    public AccountDetailed findById(final Long id) throws SimpleTransferException {
        return populator.toDetailed(service.findById(id));
    }

    public List<AccountMinimal> findByClientId(final Long id) throws SimpleTransferException {
        final List<AccountMinimal> accounts = new ArrayList<>();
        for (final Account a : service.findByClientId(id)) {
            accounts.add(populator.toMinimal(a));
        }
        return accounts;
    }

    public Long create(final AccountCreate account) throws SimpleTransferException {
        validator.validate(account);
        currencyService.validate(account.getCurrency());
        return service.create(populator.fromCreate(account));
    }

    public void deposit(final AccountDeposit account) throws SimpleTransferException {
        validator.validate(account);
        service.adjustBalance(account.getId(), account.getAmount());
    }
}
