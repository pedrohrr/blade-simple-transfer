package com.pedrohrr.simpletransfer.service;

import com.blade.ioc.annotation.Bean;
import com.pedrohrr.simpletransfer.model.Client;
import io.github.biezhi.anima.Anima;

import java.util.List;
import java.util.stream.Collectors;

@Bean
public class ClientService {

    public Long create(final Client client) {
        return client.save().asLong();
    }

    public int update(final Client client) {
        return client.update();
    }

    public List<Client> findByName(final String name) {
        return Anima.select().from(Client.class).filter(c -> c.getFirstname().contains(name) || c.getLastname().contains(name)).collect(Collectors.toList());
    }

    public Client findById(Long id) {
        return Anima.select().from(Client.class).byId(id);
    }

    public int delete(Long id) {
        return Anima.deleteById(Client.class, id);
    }

}
