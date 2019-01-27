package com.pedrohrr.simpletransfer.facade;

import com.pedrohrr.simpletransfer.data.Validator;
import com.pedrohrr.simpletransfer.data.transfer.TransferCreate;
import com.pedrohrr.simpletransfer.data.transfer.TransferDetailed;
import com.pedrohrr.simpletransfer.data.transfer.TransferMinimal;
import com.pedrohrr.simpletransfer.enumeration.TransferStatus;
import com.pedrohrr.simpletransfer.exception.SimpleTransferException;
import com.pedrohrr.simpletransfer.model.Account;
import com.pedrohrr.simpletransfer.model.Transfer;
import com.pedrohrr.simpletransfer.populator.TransferPopulator;
import com.pedrohrr.simpletransfer.service.AccountService;
import com.pedrohrr.simpletransfer.service.CurrencyService;
import com.pedrohrr.simpletransfer.service.TransferService;
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
public class TransferFacadeTest {

    @InjectMocks
    private TransferFacade facade;

    @Mock
    private TransferService service;

    @Mock
    private AccountService accountService;

    @Mock
    private TransferPopulator populator;

    @Mock
    private Validator validator;

    @Mock
    private CurrencyService currencyService;

    @Test
    public void findById() throws SimpleTransferException {
        Transfer t = mock(Transfer.class);
        when(service.findById(1l)).thenReturn(t);
        TransferDetailed td = mock(TransferDetailed.class);
        when(populator.toDetailed(t)).thenReturn(td);
        assertEquals(td, facade.findById(1l));
    }

    @Test
    public void findByAccountId() throws SimpleTransferException {
        Transfer tR = mock(Transfer.class);
        Transfer tS = mock(Transfer.class);
        when(service.findByReceiverId(1l)).thenReturn(Arrays.asList(tR));
        when(service.findBySenderId(1l)).thenReturn(Arrays.asList(tS));
        when(populator.toMinimal(tR)).thenReturn(mock(TransferMinimal.class));
        when(populator.toMinimal(tS)).thenReturn(mock(TransferMinimal.class));
        assertEquals(2, facade.findByAccountId(1l).size());
    }

    @Test
    public void findByClientId() throws SimpleTransferException {
        Transfer tR = mock(Transfer.class);
        Transfer tS = mock(Transfer.class);
        when(service.findByReceiverClientId(1l)).thenReturn(Arrays.asList(tR));
        when(service.findBySenderClientId(1l)).thenReturn(Arrays.asList(tS));
        when(populator.toMinimal(tR)).thenReturn(mock(TransferMinimal.class));
        when(populator.toMinimal(tS)).thenReturn(mock(TransferMinimal.class));
        assertEquals(2, facade.findByClientId(1l).size());
    }

    @Test
    public void create() throws SimpleTransferException {
        Transfer t = mock(Transfer.class);
        TransferCreate tc = mock(TransferCreate.class);
        when(service.create(t)).thenReturn(1l);
        doNothing().when(validator).validate(tc);
        when(populator.fromCreate(tc)).thenReturn(t);
        assertEquals(1l, facade.create(tc).longValue());
        verify(validator, times(1)).validate(tc);
    }

}