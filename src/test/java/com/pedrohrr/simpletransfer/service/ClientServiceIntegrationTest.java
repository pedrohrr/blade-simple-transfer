package com.pedrohrr.simpletransfer.service;

import com.blade.ioc.annotation.Inject;
import com.blade.test.BladeApplication;
import com.blade.test.BladeTestRunner;
import com.pedrohrr.simpletransfer.TestApplication;
import com.pedrohrr.simpletransfer.exception.DuplicateException;
import com.pedrohrr.simpletransfer.exception.NotFoundException;
import com.pedrohrr.simpletransfer.exception.SimpleTransferException;
import com.pedrohrr.simpletransfer.model.Client;
import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.MethodSorters;

import java.util.List;

import static org.junit.Assert.assertEquals;

@RunWith(BladeTestRunner.class)
@BladeApplication(TestApplication.class)
@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class ClientServiceIntegrationTest {

    @Inject
    private ClientService service;

    @Test
    public void test01create() throws DuplicateException {
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
    public void test02update() throws NotFoundException {
        final Client c = new Client();
        c.setId(2l);
        c.setFirstname("John");
        c.setLastname("Doe");
        service.update(c);
    }

    @Test
    public void test03findByName() throws NotFoundException {
        List<Client> clients = service.findByName("First");
        assertEquals(1, clients.size());
        assertEquals("AAA567", clients.get(0).getPassport());
    }

    @Test
    public void test04findById() throws NotFoundException {
        Client client = service.findById(2l);
        assertEquals("John", client.getFirstname());
        assertEquals("Doe", client.getLastname());
        assertEquals("AAA568", client.getPassport());
    }

    @Test
    public void test05delete() throws SimpleTransferException {
        service.delete(1l);
    }

    @Test(expected = DuplicateException.class)
    public void test06createDuplicateException() throws DuplicateException {
        final Client c = new Client();
        c.setFirstname("Duplicate");
        c.setLastname("Last");
        c.setPassport("AAA568");
        service.create(c);
    }

    @Test(expected = NotFoundException.class)
    public void test07updateNotFoundException() throws NotFoundException {
        final Client c = new Client();
        c.setId(1l);
        c.setFirstname("Jane");
        c.setLastname("Doe");
        service.update(c);
    }

    @Test(expected = NotFoundException.class)
    public void test08findByNameNotFoundException() throws NotFoundException {
        List<Client> clients = service.findByName("First");
        assertEquals(1, clients.size());
        assertEquals("AAA567", clients.get(0).getPassport());
    }

    @Test(expected = NotFoundException.class)
    public void test09findByIdNotFoundException() throws NotFoundException {
        Client client = service.findById(1l);
        assertEquals("John", client.getFirstname());
        assertEquals("Doe", client.getLastname());
        assertEquals("AAA568", client.getPassport());
    }

    @Test(expected = NotFoundException.class)
    public void test10deleteNotFoundException() throws SimpleTransferException {
        service.delete(1l);
    }
}