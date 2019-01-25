package com.pedrohrr.simpletransfer.data.account;

import com.pedrohrr.simpletransfer.data.Validator;
import com.pedrohrr.simpletransfer.exception.InvalidDataException;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;

import java.math.BigDecimal;

public class AccountDepositValidatorTest {


    @Rule
    public ExpectedException expect = ExpectedException.none();

    private Validator validator = new Validator();

    @Test
    public void validateAllNull() throws InvalidDataException {
        expect.expect(InvalidDataException.class);
        expect.expectMessage("amount must not be null; id must not be null");

        AccountDeposit a = new AccountDeposit();
        validator.validate(a);
    }

    @Test
    public void validateAmountNegative() throws InvalidDataException {
        expect.expect(InvalidDataException.class);
        expect.expectMessage("amount must be greater than 0");

        AccountDeposit a = new AccountDeposit();
        a.setAmount(BigDecimal.valueOf(-100));
        validator.validate(a);
    }

}