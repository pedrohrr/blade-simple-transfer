package com.pedrohrr.simpletransfer.exception;

import io.netty.handler.codec.http.HttpResponseStatus;

public class NotNullException extends SimpleTransferException {
    public NotNullException(String attribute) {
        super(String.format("Attribute %s cannot be null", attribute));
    }

    @Override
    public HttpResponseStatus getStatus() {
        return HttpResponseStatus.BAD_REQUEST;
    }
}
