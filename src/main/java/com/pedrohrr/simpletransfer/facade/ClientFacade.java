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
import com.pedrohrr.simpletransfer.service.ClientService;

import java.util.List;
import java.util.stream.Collectors;

@Bean
public class ClientFacade {
    
    @Inject
    private ClientService service;
    
    @Inject
    private Validator validator;

    public ClientDetailed findById(final Long clientId) throws SimpleTransferException {
        final Client client = service.findById(clientId);
        return toDetailed(client);
    }

    public List<ClientMinimal> findByName(final String name) throws SimpleTransferException {
        final List<Client> clients = service.findByName(name);
        return clients.stream().map(this::toMinimal).collect(Collectors.toList());
    }

    public Long create(final ClientCreate client) throws SimpleTransferException {
        validator.validate(client);
        final Client model = new Client();
        model.setFirstname(client.getFirstname());
        model.setLastname(client.getLastname());
        model.setPassport(client.getPassport());
        return service.create(model);
    }

    public void update(final ClientUpdate client) throws SimpleTransferException {
        validator.validate(client);
        final Client model = new Client();
        model.setId(client.getId());
        model.setFirstname(client.getFirstname());
        model.setLastname(client.getLastname());
        model.setPassport(client.getPassport());
        service.update(model);
    }

    public void delete(final Long clientId) throws SimpleTransferException {
        service.delete(clientId);
    }

    private ClientMinimal toMinimal(final Client client) {
        final ClientMinimal detailed = new ClientMinimal();
        detailed.setId(client.getId());
        detailed.setName(client.getFirstname() + " " + client.getLastname());
        return detailed;
    }

    private ClientDetailed toDetailed(final Client client) {
        final ClientDetailed detailed = new ClientDetailed();
        detailed.setId(client.getId());
        detailed.setFirstname(client.getFirstname());
        detailed.setLastname(client.getLastname());
        detailed.setPassport(client.getPassport());
        return detailed;
    }

}
