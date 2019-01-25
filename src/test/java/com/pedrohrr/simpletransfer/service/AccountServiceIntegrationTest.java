package com.pedrohrr.simpletransfer.service;

import com.blade.ioc.annotation.Inject;
import com.blade.test.BladeApplication;
import com.blade.test.BladeTestRunner;
import com.pedrohrr.simpletransfer.TestApplication;
import com.pedrohrr.simpletransfer.enumeration.AccountStatus;
import com.pedrohrr.simpletransfer.exception.DuplicateException;
import com.pedrohrr.simpletransfer.exception.NotEmptyAccountException;
import com.pedrohrr.simpletransfer.exception.NotFoundException;
import com.pedrohrr.simpletransfer.exception.SimpleTransferException;
import com.pedrohrr.simpletransfer.model.Account;
import com.pedrohrr.simpletransfer.model.Client;
import io.github.biezhi.anima.Anima;
import org.junit.AfterClass;
import org.junit.BeforeClass;
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
    public void test01AccountFlow() throws SimpleTransferException {
        final Client client1 = new Client();
        client1.setFirstname("John");
        client1.setLastname("Doe");
        client1.setPassport("AAA123");
        long c1 = clientService.create(client1);

        final Client client2 = new Client();
        client1.setFirstname("Jack");
        client1.setLastname("Doe");
        client1.setPassport("AAA345");
        long c2 = clientService.create(client1);

        final Account account = new Account();
        account.setClient(c1);
        account.setCurrency("USD");
        account.setIban("102939303820228");
        service.create(account);

        final Account account2 = new Account();
        account2.setClient(c1);
        account2.setCurrency("EUR");
        account2.setIban("102939303820400");
        long a2 = service.create(account2);

        Account account3 = new Account();
        account3.setClient(c2);
        account3.setCurrency("BRL");
        account3.setIban("102939303825670");
        long a3 = service.create(account3);

        account3 = service.findById(a3);
        assertEquals("BRL", account3.getCurrency());
        assertEquals("102939303825670", account3.getIban());
        assertEquals(AccountStatus.ACTIVE, account3.getStatus());
        assertEquals(BigDecimal.valueOf(0.0), account3.getBalance());

        List<Account> accounts = service.findByClientId(c1);
        assertEquals(2, accounts.size());

        service.delete(a2);
    }


    @Test(expected = DuplicateException.class)
    public void test06createDuplicateException() throws DuplicateException {
        final Account account = new Account();
        account.setCurrency("USD");
        account.setIban("102939303820228");
        service.create(account);
    }

    @Test(expected = NotFoundException.class)
    public void test07updateNotFoundException() throws NotFoundException {
        final Account account = new Account();
        account.setId(100l);
        account.setCurrency("USD");
        account.setIban("102939303820228");
        service.update(account);
    }

    @Test(expected = NotFoundException.class)
    public void test08findByNameNotFoundException() throws NotFoundException {
        service.findByClientId(100l);
    }

    @Test(expected = NotFoundException.class)
    public void test09findByIdNotFoundException() throws NotFoundException {
        service.findById(100l);
    }

    @Test(expected = NotFoundException.class)
    public void test10deleteNotFoundException() throws SimpleTransferException {
        service.delete(100l);
    }


}