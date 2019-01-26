package com.pedrohrr.simpletransfer.service;

import com.blade.ioc.annotation.Inject;
import com.pedrohrr.simpletransfer.AbstractIntegrationTest;
import com.pedrohrr.simpletransfer.enumeration.AccountStatus;
import com.pedrohrr.simpletransfer.exception.*;
import com.pedrohrr.simpletransfer.model.Account;
import com.pedrohrr.simpletransfer.model.Client;
import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runners.MethodSorters;

import java.math.BigDecimal;
import java.util.List;

import static org.junit.Assert.*;

@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class AccountServiceIntegrationTest extends AbstractIntegrationTest {

    @Inject
    private AccountService service;

    @Inject
    private ClientService clientService;

    @Test
    public void test01CreateClients() throws SimpleTransferException {
        final Client client1 = new Client();
        client1.setFirstname("John");
        client1.setLastname("Doe");
        client1.setPassport("AAA123");
        long c1 = clientService.create(client1);

        final Client client2 = new Client();
        client2.setFirstname("Jack");
        client2.setLastname("Doe");
        client2.setPassport("AAA345");
        long c2 = clientService.create(client2);

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

        service.adjustBalance(a3, BigDecimal.TEN);

        account3 = service.findById(a3);
        assertEquals("BRL", account3.getCurrency());
        assertEquals("102939303825670", account3.getIban());
        assertEquals(AccountStatus.ACTIVE, account3.getStatus());
        assertEquals(BigDecimal.TEN.doubleValue(), account3.getBalance().doubleValue(), 0.0);

        List<Account> accounts = service.findByClientId(c1);
        assertEquals(2, accounts.size());

        service.delete(a2);
    }


    @Test(expected = DuplicateException.class)
    public void test02createDuplicateException() throws DuplicateException {
        final Account account = new Account();
        account.setCurrency("USD");
        account.setIban("102939303820228");
        service.create(account);
    }

    @Test(expected = NotFoundException.class)
    public void test03updateNotFoundException() throws NotFoundException {
        final Account account = new Account();
        account.setId(100l);
        account.setCurrency("USD");
        account.setIban("102939303820228");
        service.update(account);
    }

    @Test(expected = NotFoundException.class)
    public void test04findByNameNotFoundException() throws NotFoundException {
        service.findByClientId(100l);
    }

    @Test(expected = NotFoundException.class)
    public void test05findByIdNotFoundException() throws NotFoundException {
        service.findById(100l);
    }

    @Test(expected = NotFoundException.class)
    public void test06deleteNotFoundException() throws SimpleTransferException {
        service.delete(100l);
    }

    @Test(expected = InsuficientFundsException.class)
    public void test07adjustBalanceInsuficientFundsException() throws DuplicateException, NotFoundException, InsuficientFundsException {
        final Client client1 = new Client();
        client1.setFirstname("John");
        client1.setLastname("Doe");
        client1.setPassport("AAA000");
        long c1 = clientService.create(client1);

        final Account account2 = new Account();
        account2.setClient(c1);
        account2.setCurrency("EUR");
        account2.setIban("102939303820600");
        long a2 = service.create(account2);
        service.adjustBalance(a2, BigDecimal.valueOf(-10.0));
    }

}