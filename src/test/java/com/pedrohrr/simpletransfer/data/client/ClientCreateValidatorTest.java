package com.pedrohrr.simpletransfer.data.client;

import com.pedrohrr.simpletransfer.data.Validator;
import com.pedrohrr.simpletransfer.exception.InvalidDataException;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;

public class ClientCreateValidatorTest {

    @Rule
    public ExpectedException expect = ExpectedException.none();

    private Validator validator = new Validator();

    @Test
    public void validateAllNull() throws InvalidDataException {
        expect.expect(InvalidDataException.class);
        expect.expectMessage("firstname must not be blank; lastname must not be blank; passport must not be blank");
        
        ClientCreate c = new ClientCreate();
        validator.validate(c);
    }

    @Test
    public void validateOnlyPassportNull() throws InvalidDataException {
        expect.expect(InvalidDataException.class);
        expect.expectMessage("passport must not be blank");


        ClientCreate c = new ClientCreate();
        c.setFirstname("Pedro");
        c.setLastname("Rodrigues");
        validator.validate(c);
    }

    @Test
    public void validateOnlyFirstNameNull() throws InvalidDataException {
        expect.expect(InvalidDataException.class);
        expect.expectMessage("firstname must not be blank");

        ClientCreate c = new ClientCreate();
        c.setLastname("Rodrigues");
        c.setPassport("AAAAHHHHAH");
        validator.validate(c);
    }

    @Test
    public void validateOnlyLastNameNull() throws InvalidDataException {
        expect.expect(InvalidDataException.class);
        expect.expectMessage("lastname must not be blank");

        ClientCreate c = new ClientCreate();
        c.setFirstname("Pedro");
        c.setPassport("AAAAHHHHAH");
        validator.validate(c);
    }

    @Test
    public void validateAllBlank() throws InvalidDataException {
        expect.expect(InvalidDataException.class);
        expect.expectMessage("firstname must not be blank; lastname must not be blank; passport must not be blank");

        ClientCreate c = new ClientCreate();
        c.setFirstname("");
        c.setLastname("");
        c.setPassport("");
        validator.validate(c);
    }

    @Test
    public void validateOk() throws InvalidDataException {
        ClientCreate c = new ClientCreate();
        c.setFirstname("Pedro");
        c.setLastname("Rodrigues");
        c.setPassport("AAAAHHHHAH");
        validator.validate(c);
    }
}