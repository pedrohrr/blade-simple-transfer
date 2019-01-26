package com.pedrohrr.simpletransfer.facade;

import com.blade.ioc.annotation.Bean;
import com.blade.ioc.annotation.Inject;
import com.pedrohrr.simpletransfer.data.Validator;
import com.pedrohrr.simpletransfer.data.client.ClientCreate;
import com.pedrohrr.simpletransfer.data.client.ClientDetailed;
import com.pedrohrr.simpletransfer.data.client.ClientMinimal;
import com.pedrohrr.simpletransfer.data.client.ClientUpdate;
import com.pedrohrr.simpletransfer.exception.SimpleTransferException;
import com.pedrohrr.simpletransfer.model.Client;
import com.pedrohrr.simpletransfer.populator.ClientPopulator;
import com.pedrohrr.simpletransfer.service.ClientService;

import java.util.List;
import java.util.stream.Collectors;

@Bean
public class ClientFacade {
    
    @Inject
    private ClientService service;

    @Inject
    private ClientPopulator populator;
    
    @Inject
    private Validator validator;

    public ClientDetailed findById(final Long clientId) throws SimpleTransferException {
        final Client client = service.findById(clientId);
        return populator.toDetailed(client);
    }

    public List<ClientMinimal> findByName(final String name) throws SimpleTransferException {
        final List<Client> clients = service.findByName(name);
        return clients.stream().map(c -> populator.toMinimal(c)).collect(Collectors.toList());
    }

    public Long create(final ClientCreate client) throws SimpleTransferException {
        validator.validate(client);
        return service.create(populator.fromCreate(client));
    }

    public void update(final ClientUpdate client) throws SimpleTransferException {
        validator.validate(client);
        service.update(populator.fromUpdate(client));
    }

    public void delete(final Long clientId) throws SimpleTransferException {
        service.delete(clientId);
    }

}
