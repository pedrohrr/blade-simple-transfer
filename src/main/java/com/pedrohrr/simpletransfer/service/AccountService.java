package com.pedrohrr.simpletransfer.service;

import com.blade.ioc.annotation.Bean;
import com.pedrohrr.simpletransfer.exception.DuplicateException;
import com.pedrohrr.simpletransfer.exception.InsuficientFundsException;
import com.pedrohrr.simpletransfer.exception.NotFoundException;
import com.pedrohrr.simpletransfer.model.Account;

import java.math.BigDecimal;
import java.util.List;
import static com.pedrohrr.simpletransfer.predicate.ModelPredicate.*;

@Bean
public class AccountService extends AbstractService<Account> {

    public Long create(final Account account) throws DuplicateException {
        return create(account, getIbanPredicate(account), IBAN);
    }

    public List<Account> findByClientId(final Long id) throws NotFoundException {
        return find(getClientPredicate(id));
    }

    public void adjustBalance(final Long id, final BigDecimal movement) throws InsuficientFundsException, NotFoundException {

    }

    @Override
    Class<Account> modelClass() {
        return Account.class;
    }
}