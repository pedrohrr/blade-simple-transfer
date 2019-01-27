package com.pedrohrr.simpletransfer.service;

import com.pedrohrr.simpletransfer.exception.InvalidDataException;
import org.junit.Before;
import org.junit.Test;

import java.math.BigDecimal;

import static org.junit.Assert.*;

public class CurrencyServiceTest {

    private CurrencyService service;

    @Before
    public void before() {
        service = new CurrencyService();
        service.setValidationUrl("https://api.exchangeratesapi.io/latest?base=%s");
        service.setConversionUrl("https://api.exchangeratesapi.io/latest?base=%s&symbols=%s");
    }

    @Test
    public void validate() throws InvalidDataException {
        service.validate("USD");
        service.validate("EUR");
    }

    @Test(expected = InvalidDataException.class)
    public void validateInvalidDataException() throws InvalidDataException {
        service.validate("XAD");
    }

    @Test
    public void getConversionRate() throws InvalidDataException {
        assertNotEquals(BigDecimal.ONE.doubleValue(), service.getConversionRate("USD", "EUR").doubleValue());
        assertEquals(BigDecimal.ONE.doubleValue(), service.getConversionRate("USD", "USD").doubleValue(), 0.0);
    }

}