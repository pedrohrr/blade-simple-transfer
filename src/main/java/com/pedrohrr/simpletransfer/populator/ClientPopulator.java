package com.pedrohrr.simpletransfer.populator;

import com.blade.ioc.annotation.Bean;
import com.pedrohrr.simpletransfer.data.client.ClientCreate;
import com.pedrohrr.simpletransfer.data.client.ClientDetailed;
import com.pedrohrr.simpletransfer.data.client.ClientMinimal;
import com.pedrohrr.simpletransfer.data.client.ClientUpdate;
import com.pedrohrr.simpletransfer.model.Client;

@Bean
public class ClientPopulator {

    public ClientMinimal toMinimal(final Client model) {
        final ClientMinimal detailed = new ClientMinimal();
        detailed.setId(model.getId());
        detailed.setName(model.getFirstname() + " " + model.getLastname());
        return detailed;
    }

    public ClientDetailed toDetailed(final Client model) {
        final ClientDetailed detailed = new ClientDetailed();
        detailed.setId(model.getId());
        detailed.setFirstname(model.getFirstname());
        detailed.setLastname(model.getLastname());
        detailed.setPassport(model.getPassport());
        return detailed;
    }

    public Client fromCreate(final ClientCreate client) {
        final Client model = new Client();
        model.setFirstname(client.getFirstname());
        model.setLastname(client.getLastname());
        model.setPassport(client.getPassport());
        return model;
    }

    public Client fromUpdate(final ClientUpdate client) {
        final Client model = new Client();
        model.setId(client.getId());
        model.setFirstname(client.getFirstname());
        model.setLastname(client.getLastname());
        model.setPassport(client.getPassport());
        return model;
    }

}
