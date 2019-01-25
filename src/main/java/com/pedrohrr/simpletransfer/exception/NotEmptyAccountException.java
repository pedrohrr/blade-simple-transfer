package com.pedrohrr.simpletransfer.exception;

import io.netty.handler.codec.http.HttpResponseStatus;

public class NotEmptyAccountException extends SimpleTransferException {
    public NotEmptyAccountException() {
        super("The account balance is not empty, therefore it cannot be erased");
    }

    @Override
    public HttpResponseStatus getStatus() {
        return HttpResponseStatus.CONFLICT;
    }
}
