package com.pedrohrr.simpletransfer.exception;

import io.netty.handler.codec.http.HttpResponseStatus;

public class NotFoundException extends SimpleTransferException {
    public NotFoundException(String model) {
        super(String.format("No %s found for the given query", model));
    }

    @Override
    public HttpResponseStatus getStatus() {
        return HttpResponseStatus.NOT_FOUND;
    }
}
