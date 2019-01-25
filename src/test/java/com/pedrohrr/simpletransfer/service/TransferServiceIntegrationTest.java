package com.pedrohrr.simpletransfer.service;

import com.blade.ioc.annotation.Inject;
import com.blade.test.BladeApplication;
import com.blade.test.BladeTestRunner;
import com.pedrohrr.simpletransfer.TestApplication;
import com.pedrohrr.simpletransfer.enumeration.AccountStatus;
import com.pedrohrr.simpletransfer.exception.NotFoundException;
import com.pedrohrr.simpletransfer.exception.SimpleTransferException;
import com.pedrohrr.simpletransfer.model.Account;
import com.pedrohrr.simpletransfer.model.Client;
import com.pedrohrr.simpletransfer.model.Transfer;
import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.MethodSorters;

import java.math.BigDecimal;
import java.util.List;

import static org.junit.Assert.assertEquals;

@RunWith(BladeTestRunner.class)
@BladeApplication(TestApplication.class)
@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class TransferServiceIntegrationTest {

    @Inject
    private TransferService service;

    @Inject
    private AccountService accountService;

    @Inject
    private ClientService clientService;

    @Test
    public void test01AccountFlow() throws SimpleTransferException {
        final Client client1 = new Client();
        client1.setFirstname("John");
        client1.setLastname("Doe");
        client1.setPassport("BBB123");
        long c1 = clientService.create(client1);

        final Client client2 = new Client();
        client2.setFirstname("Jack");
        client2.setLastname("Doe");
        client2.setPassport("BBB345");
        long c2 = clientService.create(client2);

        final Account account = new Account();
        account.setClient(c1);
        account.setCurrency("USD");
        account.setIban("1029393080000000");
        long a1 = accountService.create(account);

        final Account account2 = new Account();
        account2.setClient(c1);
        account2.setCurrency("EUR");
        account2.setIban("102939308003405");
        long a2 = accountService.create(account2);

        final Account account3 = new Account();
        account3.setClient(c2);
        account3.setCurrency("BRL");
        account3.setIban("102939308003800");
        long a3 = accountService.create(account3);

        final Transfer transfer1 = new Transfer();
        transfer1.setAmount(BigDecimal.TEN);
        transfer1.setReceiver(a2);
        transfer1.setSender(a1);
        long t1 = service.create(transfer1);

        final Transfer transfer2 = new Transfer();
        transfer2.setAmount(BigDecimal.TEN);
        transfer2.setReceiver(a3);
        transfer2.setSender(a2);
        long t2 = service.create(transfer2);

        final Transfer transfer3 = new Transfer();
        transfer3.setAmount(BigDecimal.TEN);
        transfer3.setReceiver(a3);
        transfer3.setSender(a1);
        long t3 = service.create(transfer3);

        List<Transfer> toA3 = service.findByReceiverId(a3);
        assertEquals(2, toA3.size());

        List<Transfer> fromA1 = service.findBySenderId(a1);
        assertEquals(2, fromA1.size());

        List<Transfer> toC2 = service.findByReceiverClientId(c2);
        assertEquals(2, toC2.size());

        List<Transfer> fromC1 = service.findBySenderClientId(c1);
        assertEquals(3, fromC1.size());

    }

    @Test(expected = NotFoundException.class)
    public void findBySenderIdNotFoundException() throws NotFoundException {
        service.findBySenderId(100l);
    }

    @Test(expected = NotFoundException.class)
    public void findByReceiverIdNotFoundException() throws NotFoundException {
        service.findByReceiverId(100l);
    }

    @Test(expected = NotFoundException.class)
    public void findBySenderAccountIdNotFoundException() throws NotFoundException {
        service.findBySenderClientId(100l);
    }

    @Test(expected = NotFoundException.class)
    public void findByReceiverAccountIdNotFoundException() throws NotFoundException {
        service.findByReceiverClientId(100l);
    }
}