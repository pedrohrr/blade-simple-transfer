package com.pedrohrr.simpletransfer.facade;

import com.blade.ioc.annotation.Bean;
import com.blade.ioc.annotation.Inject;
import com.pedrohrr.simpletransfer.data.Validator;
import com.pedrohrr.simpletransfer.data.client.ClientCreate;
import com.pedrohrr.simpletransfer.data.client.ClientDetailed;
import com.pedrohrr.simpletransfer.data.client.ClientMinimal;
import com.pedrohrr.simpletransfer.data.client.ClientUpdate;
import com.pedrohrr.simpletransfer.exception.SimpleTransferException;
import com.pedrohrr.simpletransfer.service.ClientService;

import java.util.List;

@Bean
public class ClientFacade {
    
    @Inject
    private ClientService service;
    
    @Inject
    private Validator validator;

    public ClientDetailed findById(final Long clientId) throws SimpleTransferException {
        return null;
    }

    public List<ClientMinimal> findByName(final String name) throws SimpleTransferException {
        return null;
    }

    public Long create(final ClientCreate client) throws SimpleTransferException {
        return null;
    }

    public void update(final ClientUpdate client) throws SimpleTransferException {

    }

    public void delete(final Long clientId) throws SimpleTransferException {

    }

}
