package com.pedrohrr.simpletransfer.service;

import com.blade.ioc.annotation.Inject;
import com.blade.test.BladeApplication;
import com.blade.test.BladeTestRunner;
import com.pedrohrr.simpletransfer.TestApplication;
import com.pedrohrr.simpletransfer.enumeration.AccountStatus;
import com.pedrohrr.simpletransfer.exception.DuplicateException;
import com.pedrohrr.simpletransfer.exception.NotEmptyAccountException;
import com.pedrohrr.simpletransfer.exception.NotFoundException;
import com.pedrohrr.simpletransfer.model.Account;
import com.pedrohrr.simpletransfer.model.Client;
import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.MethodSorters;

import java.math.BigDecimal;
import java.util.List;

import static org.junit.Assert.*;

@RunWith(BladeTestRunner.class)
@BladeApplication(TestApplication.class)
@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class AccountServiceIntegrationTest {

    @Inject
    private AccountService service;

    @Inject
    private ClientService clientService;

    @Test
    public void test01create() throws DuplicateException {
        final Client c = new Client();
        c.setFirstname("John");
        c.setLastname("Doe");
        c.setPassport("AAA567");
        assertEquals(1l, clientService.create(c).longValue());

        final Account account = new Account();
        account.setClient(c);
        account.setCurrency("USD");
        account.setIban("102939303820228");
        assertEquals(1l, service.create(account).longValue());

        final Account account2 = new Account();
        account2.setClient(c);
        account2.setCurrency("EUR");
        account2.setIban("102939303820400");
        assertEquals(2l, service.create(account2).longValue());

        final Client c2 = new Client();
        c.setFirstname("Jack");
        c.setLastname("Doe");
        c.setPassport("AAA560");
        assertEquals(1l, clientService.create(c).longValue());

        final Account account3 = new Account();
        account3.setClient(c2);
        account3.setCurrency("BRL");
        account3.setIban("102939303825670");
        assertEquals(3l, service.create(account3).longValue());
    }

    @Test
    public void test03findById() throws NotFoundException {
        Account account = service.findById(3l);
        assertEquals("BRL", account.getCurrency());
        assertEquals("102939303825670", account.getIban());
        assertEquals(AccountStatus.ACTIVE, account.getStatus());
        assertEquals(BigDecimal.valueOf(0.0), account.getBalance());
    }

    @Test
    public void test04findByClientId() throws NotFoundException {
        List<Account> accounts = service.findByClientId(1l);
        assertEquals(2, accounts.size());
    }

    @Test
    public void test05delete() throws NotFoundException, NotEmptyAccountException {
        service.delete(2l);
    }

}