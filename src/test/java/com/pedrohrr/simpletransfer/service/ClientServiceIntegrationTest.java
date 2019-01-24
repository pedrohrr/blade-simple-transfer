package com.pedrohrr.simpletransfer.service;

import com.blade.ioc.annotation.Inject;
import com.blade.test.BladeApplication;
import com.blade.test.BladeTestRunner;
import com.pedrohrr.simpletransfer.SimpleTransferApplication;
import com.pedrohrr.simpletransfer.TestApplication;
import com.pedrohrr.simpletransfer.model.Client;
import org.junit.Test;
import org.junit.runner.RunWith;

import java.util.List;

import static org.junit.Assert.*;

@RunWith(BladeTestRunner.class)
@BladeApplication(TestApplication.class)
public class ClientServiceIntegrationTest {

    @Inject
    private ClientService service;

    @Test
    public void test1create() {
        final Client c = new Client();
        c.setFirstname("First");
        c.setLastname("Last");
        c.setPassport("AAA567");
        assertEquals(1l, service.create(c).longValue());

        final Client c2 = new Client();
        c2.setFirstname("First");
        c2.setLastname("Last");
        c2.setPassport("AAA568");
        assertEquals(2l, service.create(c2).longValue());
    }

    @Test
    public void test2update() {
        final Client c = new Client();
        c.setId(2l);
        c.setFirstname("John");
        c.setLastname("Doe");
        service.update(c);
    }

    @Test
    public void test3findByName() {
        List<Client> clients = service.findByName("First");
        assertEquals(1, clients.size());
        assertEquals("AAA567", clients.get(0).getPassport());
    }

    @Test
    public void test4findById() {
        Client client = service.findById(2l);
        assertEquals("John", client.getFirstname());
        assertEquals("Doe", client.getLastname());
        assertEquals("AAA568", client.getPassport());
    }

    @Test
    public void test5delete() {
        service.delete(1l);
    }
}