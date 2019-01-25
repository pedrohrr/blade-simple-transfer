package com.pedrohrr.simpletransfer.service;

import com.blade.ioc.annotation.Bean;
import com.pedrohrr.simpletransfer.exception.DuplicateException;
import com.pedrohrr.simpletransfer.exception.NotEmptyAccountException;
import com.pedrohrr.simpletransfer.exception.NotFoundException;
import com.pedrohrr.simpletransfer.model.Account;

import java.util.List;

@Bean
public class AccountService extends AbstractService<Account> {

    public Long create(final Account account) throws DuplicateException {
        account.setId(null);



        return account.save().asLong();
    }

    public Account findById(final Long id) throws NotFoundException {
        return null;
    }

    public void delete(final Long id) throws NotFoundException, NotEmptyAccountException {

    }

    public List<Account> findByClientId(final Long id) throws NotFoundException {
        return null;
    }

    @Override
    Class<Account> modelClass() {
        return Account.class;
    }
}