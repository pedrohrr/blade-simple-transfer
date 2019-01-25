package com.pedrohrr.simpletransfer.service;

import com.blade.ioc.annotation.Bean;
import com.pedrohrr.simpletransfer.exception.DuplicateException;
import com.pedrohrr.simpletransfer.exception.NotEmptyAccountException;
import com.pedrohrr.simpletransfer.exception.NotFoundException;
import com.pedrohrr.simpletransfer.exception.UnmodifiableAttributeException;
import com.pedrohrr.simpletransfer.model.Account;

import java.util.List;

@Bean
public class AccountService {

    public Long create(final Account account) throws DuplicateException {
        return null;
    }

    public Account findById(final Long id) throws NotFoundException {
        return null;
    }

    public void delete(final Long id) throws NotFoundException, NotEmptyAccountException {

    }

    public List<Account> findByClientId(final Long id) throws NotFoundException {
        return null;
    }

}