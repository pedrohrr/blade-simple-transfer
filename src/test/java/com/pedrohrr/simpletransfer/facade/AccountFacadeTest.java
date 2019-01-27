package com.pedrohrr.simpletransfer.facade;

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
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import java.math.BigDecimal;
import java.util.Arrays;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.*;

@RunWith(MockitoJUnitRunner.class)
public class AccountFacadeTest {

    @InjectMocks
    private AccountFacade facade;

    @Mock
    private AccountService service;

    @Mock
    private AccountPopulator populator;

    @Mock
    private Validator validator;

    @Mock
    private CurrencyService currencyService;

    @Test
    public void findById() throws SimpleTransferException {
        Account a1 = mock(Account.class);
        when(service.findById(1l)).thenReturn(a1);
        AccountDetailed ad1 = mock(AccountDetailed.class);
        when(populator.toDetailed(a1)).thenReturn(ad1);
        assertEquals(ad1, facade.findById(1l));
    }

    @Test
    public void findByClientId() throws SimpleTransferException {
        Account a1 = mock(Account.class);
        Account a2 = mock(Account.class);
        when(service.findByClientId(1l)).thenReturn(Arrays.asList(a1, a2));
        AccountMinimal am1 = mock(AccountMinimal.class);
        AccountMinimal am2 = mock(AccountMinimal.class);
        when(populator.toMinimal(a1)).thenReturn(am1);
        when(populator.toMinimal(a2)).thenReturn(am2);
        assertEquals(2, facade.findByClientId(1l).size());
    }

    @Test
    public void create() throws SimpleTransferException {
        Account a1 = mock(Account.class);
        when(service.create(a1)).thenReturn(1l);
        AccountCreate ac1 = mock(AccountCreate.class);
        doNothing().when(validator).validate(ac1);
        when(ac1.getCurrency()).thenReturn("USD");
        doNothing().when(currencyService).validate("USD");
        when(populator.fromCreate(ac1)).thenReturn(a1);
        assertEquals(1l, facade.create(ac1).longValue());
        verify(validator, times(1)).validate(ac1);
        verify(currencyService, times(1)).validate("USD");
    }

    @Test
    public void deposit() throws SimpleTransferException {
        doNothing().when(service).adjustBalance(1l, BigDecimal.TEN);
        AccountDeposit ad1 = mock(AccountDeposit.class);
        when(ad1.getId()).thenReturn(1l);
        when(ad1.getAmount()).thenReturn(BigDecimal.TEN);
        doNothing().when(validator).validate(ad1);
        facade.deposit(ad1);
        verify(validator, times(1)).validate(ad1);
    }
}