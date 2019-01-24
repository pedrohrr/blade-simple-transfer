package com.pedrohrr.simpletransfer.exception;

import io.netty.handler.codec.http.HttpResponseStatus;

public abstract class SimpleTransferException extends Exception {
    public SimpleTransferException(String message) {
        super(message);
    }

    public abstract HttpResponseStatus getStatus();
}
