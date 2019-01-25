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
        final Account account = findById(id);
        account.setBalance(applyMovement(movement, account));
        update(account);
    }

    private BigDecimal applyMovement(final BigDecimal movement, final Account account) throws InsuficientFundsException {
        final BigDecimal result = account.getBalance().add(movement);
        if (movement.signum() == -1 && result.signum() == -1) {
            throw new InsuficientFundsException();
        }
        return result;
    }

    @Override
    Class<Account> modelClass() {
        return Account.class;
    }
}