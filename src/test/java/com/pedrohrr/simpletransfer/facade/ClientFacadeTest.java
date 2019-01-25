package com.pedrohrr.simpletransfer.facade;

import com.pedrohrr.simpletransfer.data.Validator;
import com.pedrohrr.simpletransfer.data.client.ClientCreate;
import com.pedrohrr.simpletransfer.data.client.ClientDetailed;
import com.pedrohrr.simpletransfer.data.client.ClientMinimal;
import com.pedrohrr.simpletransfer.data.client.ClientUpdate;
import com.pedrohrr.simpletransfer.exception.SimpleTransferException;
import com.pedrohrr.simpletransfer.service.ClientService;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import java.util.List;

import static org.junit.Assert.*;

@RunWith(MockitoJUnitRunner.class)
public class ClientFacadeTest {

    @InjectMocks
    private ClientFacade facade;

    @Mock
    private ClientService service;

    @Mock
    private Validator validator;

    public void findById() throws SimpleTransferException {

    }

    public void findByName() throws SimpleTransferException {

    }

    public void create() throws SimpleTransferException {

    }

    public void update() throws SimpleTransferException {

    }

    public void delete() throws SimpleTransferException {

    }

}