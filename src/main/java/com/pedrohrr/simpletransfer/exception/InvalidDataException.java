package com.pedrohrr.simpletransfer.exception;

import io.netty.handler.codec.http.HttpResponseStatus;

public class InvalidDataException extends SimpleTransferException {
    public InvalidDataException(String message) {
        super(message);
    }

    @Override
    public HttpResponseStatus getStatus() {
        return HttpResponseStatus.BAD_REQUEST;
    }
}
