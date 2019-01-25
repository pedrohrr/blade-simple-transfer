package com.pedrohrr.simpletransfer.data.client;

import com.pedrohrr.simpletransfer.data.Validator;
import com.pedrohrr.simpletransfer.exception.InvalidDataException;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;

public class ClientUpdateValidatorTest {

    @Rule
    public ExpectedException expect = ExpectedException.none();

    private Validator validator = new Validator();

    @Test
    public void validateIdNull() throws InvalidDataException {
        expect.expect(InvalidDataException.class);
        expect.expectMessage("id must not be null");

        ClientUpdate c = new ClientUpdate();
        validator.validate(c);
    }

    @Test
    public void validateIdNegative() throws InvalidDataException {
        expect.expect(InvalidDataException.class);
        expect.expectMessage("id must be greater than 0");

        ClientUpdate c = new ClientUpdate();
        c.setId(-1l);
        validator.validate(c);
    }

    @Test
    public void validateOk() throws InvalidDataException {
        ClientUpdate c = new ClientUpdate();
        c.setId(3l);
        validator.validate(c);
    }

}