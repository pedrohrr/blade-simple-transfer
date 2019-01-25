package com.pedrohrr.simpletransfer.facade;

import com.pedrohrr.simpletransfer.data.Validator;
import com.pedrohrr.simpletransfer.data.client.ClientCreate;
import com.pedrohrr.simpletransfer.data.client.ClientDetailed;
import com.pedrohrr.simpletransfer.data.client.ClientMinimal;
import com.pedrohrr.simpletransfer.data.client.ClientUpdate;
import com.pedrohrr.simpletransfer.exception.SimpleTransferException;
import com.pedrohrr.simpletransfer.model.Client;
import com.pedrohrr.simpletransfer.service.ClientService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import java.util.Arrays;
import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.*;

@RunWith(MockitoJUnitRunner.class)
public class ClientFacadeTest {

    @InjectMocks
    private ClientFacade facade;

    @Mock
    private ClientService service;

    @Mock
    private Validator validator;

    @Test
    public void findById() throws SimpleTransferException {
        final Client mockClient = mock(Client.class);
        when(mockClient.getId()).thenReturn(1l);
        when(mockClient.getPassport()).thenReturn("AAAAAF");
        when(mockClient.getFirstname()).thenReturn("John");
        when(mockClient.getLastname()).thenReturn("Doe");
        when(service.findById(1l)).thenReturn(mockClient);
        final ClientDetailed client = facade.findById(1l);
        assertEquals("AAAAAF", client.getPassport());
        assertEquals("John", client.getFirstname());
        assertEquals("Doe", client.getLastname());
    }

    @Test
    public void findByName() throws SimpleTransferException {
        Client john1 = mock(Client.class);
        when(john1.getId()).thenReturn(1l);
        when(john1.getFirstname()).thenReturn("Johnathan");
        when(john1.getLastname()).thenReturn("Doe");
        Client john2 = mock(Client.class);
        when(john1.getId()).thenReturn(2l);
        when(john1.getFirstname()).thenReturn("Doe");
        when(john1.getLastname()).thenReturn("Johnson");
        when(service.findByName("John")).thenReturn(Arrays.asList(john1, john2));
        List<ClientMinimal> clients = facade.findByName("John");
        assertEquals(2, clients.size());
    }

    @Test
    public void create() throws SimpleTransferException {
        when(service.create(any(Client.class))).thenReturn(1l);
        assertEquals(1l, facade.create(mock(ClientCreate.class)).longValue());
    }

    @Test
    public void update() throws SimpleTransferException {
        facade.update(mock(ClientUpdate.class));
        verify(service, times(1)).update(any(Client.class));
    }

    @Test
    public void delete() throws SimpleTransferException {
        facade.delete(1l);
        verify(service, times(1)).delete(1l);
    }

}