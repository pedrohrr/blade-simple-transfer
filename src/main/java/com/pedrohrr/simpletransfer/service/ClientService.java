package com.pedrohrr.simpletransfer.service;

import com.blade.ioc.annotation.Bean;
import com.pedrohrr.simpletransfer.exception.DuplicateException;
import com.pedrohrr.simpletransfer.exception.NotFoundException;
import com.pedrohrr.simpletransfer.model.Client;

import java.util.List;

import static com.pedrohrr.simpletransfer.predicate.ModelPredicate.*;

@Bean
public class ClientService extends AbstractService<Client> {

    public Long create(final Client client) throws DuplicateException {
        return create(client, getPassportPredicate(client.getPassport()), PASSPORT);
    }

    public List<Client> findByName(final String name) throws NotFoundException {
        return find(getNamePredicate(name));
    }

    @Override
    Class<Client> modelClass() {
        return Client.class;
    }
}
