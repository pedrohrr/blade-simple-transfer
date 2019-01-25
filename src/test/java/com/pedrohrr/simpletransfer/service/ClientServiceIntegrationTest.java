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
    public void test01ClientFlow() throws SimpleTransferException {
        final Client cliente = new Client();
        cliente.setFirstname("First");
        cliente.setLastname("Last");
        cliente.setPassport("AAA567");
        long c1 = service.create(cliente);

        final Client cliente2 = new Client();
        cliente2.setFirstname("First");
        cliente2.setLastname("Last");
        cliente2.setPassport("AAA568");
        long c2 = service.create(cliente2);

        final Client c = new Client();
        c.setId(c2);
        c.setFirstname("John");
        c.setLastname("Doe");
        service.update(c);

        List<Client> clients = service.findByName("First");
        assertEquals(1, clients.size());
        assertEquals("AAA567", clients.get(0).getPassport());

        Client client = service.findById(c2);
        assertEquals("John", client.getFirstname());
        assertEquals("Doe", client.getLastname());
        assertEquals("AAA568", client.getPassport());

        service.delete(c1);
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
        c.setId(100l);
        c.setFirstname("Jane");
        c.setLastname("Doe");
        service.update(c);
    }

    @Test(expected = NotFoundException.class)
    public void test08findByNameNotFoundException() throws NotFoundException {
        service.findByName("NotFound");
    }

    @Test(expected = NotFoundException.class)
    public void test09findByIdNotFoundException() throws NotFoundException {
        service.findById(100l);
    }

    @Test(expected = NotFoundException.class)
    public void test10deleteNotFoundException() throws SimpleTransferException {
        service.delete(100l);
    }

}