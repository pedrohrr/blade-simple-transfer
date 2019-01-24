package com.pedrohrr.simpletransfer.service;

import com.blade.ioc.annotation.Bean;
import com.pedrohrr.simpletransfer.exception.DuplicateException;
import com.pedrohrr.simpletransfer.exception.NotFoundException;
import com.pedrohrr.simpletransfer.model.Client;
import io.github.biezhi.anima.Anima;

import java.util.List;
import java.util.function.Predicate;
import java.util.stream.Collectors;

import static com.pedrohrr.simpletransfer.predicate.ClientPredicate.*;
import static com.pedrohrr.simpletransfer.predicate.ClientPredicate.getPassportPredicate;

@Bean
public class ClientService {

    public Long create(final Client client) throws DuplicateException {
        client.setId(null);
        if (!find(getPassportPredicate(client.getPassport())).isEmpty()) {
            throw new DuplicateException(PASSPORT);
        }
        return client.save().asLong();
    }

    public void update(final Client client) throws NotFoundException {
        if (client.update() == 0) {
            throw new NotFoundException(MODEL);
        }
    }

    public List<Client> findByName(final String name) throws NotFoundException {
        final List<Client> clients = find(getNamePredicate(name));

        if (clients.isEmpty()) {
            throw new NotFoundException(MODEL);
        }

        return clients;
    }

    public Client findById(Long id) throws NotFoundException {
        final Client client = Anima.select().from(Client.class).byId(id);

        if (client == null) {
            throw new NotFoundException(MODEL);
        }

        return client;
    }

    public void delete(Long id) throws NotFoundException {
        if (Anima.deleteById(Client.class, id) == 0) {
            throw new NotFoundException(MODEL);
        }
    }

    private List<Client> find(Predicate<Client> predicate) {
        return Anima.select().from(Client.class).filter(predicate).collect(Collectors.toList());
    }
}
