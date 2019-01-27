package com.pedrohrr.simpletransfer.service;

import com.blade.ioc.annotation.Bean;
import com.blade.ioc.annotation.Value;
import com.blade.kit.JsonKit;
import com.pedrohrr.simpletransfer.exception.InvalidDataException;
import lombok.Getter;
import lombok.Setter;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.io.IOUtils;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.HttpClientBuilder;

import java.io.IOException;
import java.math.BigDecimal;
import java.util.Map;

@Bean
@Slf4j
public class CurrencyService {

    private static final String UNABLE_TO_CONNECT_TO_CURRENCY_API = "Unable to connect to currency api";
    private static final String ACCEPT = "accept";
    private static final String APPLICATION_JSON = "application/json";
    private static final String CURRENCY_IS_NOT_SUPPORTED = "Currency is not supported";

    @Setter
    @Value(name = "currency.validation.api")
    private String validationUrl;

    @Setter
    @Value(name = "currency.conversion.api")
    private String conversionUrl;

    public void validate(final String currency) throws InvalidDataException {
        final Response resp = getResponse(String.format(validationUrl, currency));
        if (resp.getError() != null) {
            throw new InvalidDataException(CURRENCY_IS_NOT_SUPPORTED);
        }
    }

    public BigDecimal getConversionRate(final String base, final String comparison) throws InvalidDataException {
        final Response resp = getResponse(String.format(conversionUrl, base, comparison));
        return BigDecimal.valueOf(resp.getRates().get(comparison));
    }

    private Response getResponse(final String url) throws InvalidDataException {
        try {
            final HttpClient client = HttpClientBuilder.create().build();
            final HttpGet request = new HttpGet(url);
            request.addHeader(ACCEPT, APPLICATION_JSON);
            final String stringResponse = IOUtils.toString(client.execute(request).getEntity().getContent());
            return JsonKit.formJson(stringResponse, Response.class);
        } catch (IOException e) {
            log.error(UNABLE_TO_CONNECT_TO_CURRENCY_API, e);
            throw new InvalidDataException(UNABLE_TO_CONNECT_TO_CURRENCY_API);
        }
    }

    @Getter @Setter
    public static class Response {
        private String base;
        private String date;
        private String error;
        private Map<String, Double> rates;
    }

}