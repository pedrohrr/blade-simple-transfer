package com.pedrohrr.simpletransfer.data.transfer;

import com.pedrohrr.simpletransfer.data.Validator;
import com.pedrohrr.simpletransfer.exception.InvalidDataException;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;

import java.math.BigDecimal;

public class TransferCreateValidatorTest {

    @Rule
    public ExpectedException expect = ExpectedException.none();

    private Validator validator = new Validator();

    @Test
    public void validateAllNull() throws InvalidDataException {
        expect.expect(InvalidDataException.class);
        expect.expectMessage("amount must not be null; receiver must not be null; sender must not be null");

        final TransferCreate tr = new TransferCreate();
        validator.validate(tr);
    }

    @Test
    public void validateAmountNegative() throws InvalidDataException {
        expect.expect(InvalidDataException.class);
        expect.expectMessage("amount must be greater than 0");

        final TransferCreate tr = new TransferCreate();
        tr.setAmount(BigDecimal.valueOf(-200));
        tr.setReceiver(3l);
        tr.setSender(3l);
        validator.validate(tr);
    }

}