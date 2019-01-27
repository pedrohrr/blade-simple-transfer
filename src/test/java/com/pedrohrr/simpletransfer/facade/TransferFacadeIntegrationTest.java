package com.pedrohrr.simpletransfer.facade;

import com.blade.ioc.annotation.Inject;
import com.pedrohrr.simpletransfer.AbstractIntegrationTest;
import com.pedrohrr.simpletransfer.data.account.AccountCreate;
import com.pedrohrr.simpletransfer.data.account.AccountDeposit;
import com.pedrohrr.simpletransfer.data.client.ClientCreate;
import com.pedrohrr.simpletransfer.data.transfer.TransferCreate;
import com.pedrohrr.simpletransfer.data.transfer.TransferMinimal;
import com.pedrohrr.simpletransfer.enumeration.TransferStatus;
import com.pedrohrr.simpletransfer.exception.SimpleTransferException;
import org.junit.Before;
import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runners.MethodSorters;

import java.math.BigDecimal;
import java.util.List;
import java.util.Set;

import static org.junit.Assert.*;

@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class TransferFacadeIntegrationTest extends AbstractIntegrationTest {

    @Inject
    private ClientFacade clientFacade;

    @Inject
    private AccountFacade accountFacade;

    @Inject
    private TransferFacade transferFacade;

    private static long hulk, ironman, captain, a1Hulk, a2Hulk, a1Ironman, a2Ironman, a3Ironman, a1Captain;

    @Test
    public void test01CreateAccounts() throws SimpleTransferException {
        hulk = createClient("Bruce", "Banner", "HHHHHH");
        ironman = createClient("Tony", "Stark", "IIIII");
        captain = createClient("Steve", "Rogers", "CCCCCC");

        a1Hulk = createAccount(hulk, "USD", "0001", 10000.0);
        a2Hulk = createAccount(hulk, "EUR", "0002", 300.0);

        a1Ironman = createAccount(ironman, "USD", "0003", 10000000000.0);
        a2Ironman = createAccount(ironman, "EUR", "0004", 20000000.0);
        a3Ironman = createAccount(ironman, "BRL", "0005", 50000000.0);

        a1Captain = createAccount(captain, "USD", "0006", 20.0);
    }

    @Test
    public void test02CreateTransfers() throws SimpleTransferException {
        createTransfer(a1Hulk, a1Captain, 200.0);
        createTransfer(a1Ironman, a1Captain, 10000.0);
        createTransfer(a3Ironman, a2Hulk, 500.0);
        createTransfer(a1Ironman, a2Ironman, 3000.0);
        createTransfer(a1Captain, a2Hulk, 10000000.0);
    }

    @Test
    public void test03ProcessTransfers() throws SimpleTransferException {
        transferFacade.process();
    }

    @Test
    public void test04ValidateTransfers() throws SimpleTransferException {
        Set<TransferMinimal> hulkTransfers = transferFacade.findByClientId(hulk);
        Set<TransferMinimal> ironManTransfers = transferFacade.findByClientId(ironman);
        Set<TransferMinimal> captainTransfers = transferFacade.findByClientId(captain);

        assertEquals(2L, getSizeByStatus(hulkTransfers, TransferStatus.COMPLETED));
        assertEquals(3L, getSizeByStatus(ironManTransfers, TransferStatus.COMPLETED));
        assertEquals(2L, getSizeByStatus(captainTransfers, TransferStatus.COMPLETED));
        assertEquals(1L, getSizeByStatus(captainTransfers, TransferStatus.CANCELED));
        assertEquals(0L, getSizeByStatus(hulkTransfers, TransferStatus.CANCELED));
        assertEquals(0L, getSizeByStatus(ironManTransfers, TransferStatus.CANCELED));
    }

    @Test
    public void test04ValidateAccountBalances() throws SimpleTransferException {
        assertEquals(0.0, accountFacade.findById(a1Hulk).getBalance().doubleValue(), 0.0);
    }

    private long getSizeByStatus(Set<TransferMinimal> transfers, TransferStatus status) {
        return transfers.stream().filter(t -> t.getStatus().equals(status)).count();
    }

    private long createClient(String firstname, String lastname, String passport) throws SimpleTransferException {
        final ClientCreate cc = new ClientCreate();
        cc.setFirstname(firstname);
        cc.setLastname(lastname);
        cc.setPassport(passport);
        return clientFacade.create(cc);
    }

    private long createAccount(long client, final String currency, final String iban, final Double balance) throws SimpleTransferException {
        final AccountCreate ac = new AccountCreate();
        ac.setClient(client);
        ac.setCurrency(currency);
        ac.setIban(iban);
        final Long accountId = accountFacade.create(ac);

        final AccountDeposit ad = new AccountDeposit();
        ad.setAmount(BigDecimal.valueOf(balance));
        ad.setId(accountId);
        accountFacade.deposit(ad);

        return accountId;
    }

    private long createTransfer(long sender, long receiver, double amount) throws SimpleTransferException {
        final TransferCreate tc = new TransferCreate();
        tc.setSender(sender);
        tc.setReceiver(receiver);
        tc.setAmount(BigDecimal.valueOf(amount));
        return transferFacade.create(tc);
    }
}