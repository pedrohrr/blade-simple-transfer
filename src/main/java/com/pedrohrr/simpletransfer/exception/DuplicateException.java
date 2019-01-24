package com.pedrohrr.simpletransfer.exception;

import io.netty.handler.codec.http.HttpResponseStatus;

import java.util.Arrays;

public class DuplicateException extends SimpleTransferException {
    public DuplicateException(String... attributes) {
        super(String.format("Attributes %s are unique", Arrays.toString(attributes)));
    }

    @Override
    protected HttpResponseStatus getStatus() {
        return null;
    }
}
