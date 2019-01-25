package com.pedrohrr.simpletransfer.data.account;

import com.pedrohrr.simpletransfer.data.Validator;
import com.pedrohrr.simpletransfer.exception.InvalidDataException;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;

public class AccountCreateValidatorTest {

    @Rule
    public ExpectedException expect = ExpectedException.none();

    private Validator validator = new Validator();

    @Test
    public void validateAllNull() throws InvalidDataException {
        expect.expect(InvalidDataException.class);
        expect.expectMessage("client must not be null; currency must not be blank; iban must not be blank");

        AccountCreate a = new AccountCreate();
        validator.validate(a);
    }

    @Test
    public void validateClientNegative() throws InvalidDataException {
        expect.expect(InvalidDataException.class);
        expect.expectMessage("client must be greater than 0");

        AccountCreate a = new AccountCreate();
        a.setClient(-2l);
        a.setCurrency("USD");
        a.setIban("000000000");
        validator.validate(a);
    }

    @Test
    public void validateCurrencyWrongLength() throws InvalidDataException {
        expect.expect(InvalidDataException.class);
        expect.expectMessage("currency must be 3 characters");

        AccountCreate a = new AccountCreate();
        a.setClient(4l);
        a.setCurrency("USDAA");
        a.setIban("000000000");
        validator.validate(a);
    }

}