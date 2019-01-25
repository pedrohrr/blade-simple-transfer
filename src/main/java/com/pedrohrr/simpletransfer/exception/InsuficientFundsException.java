package com.pedrohrr.simpletransfer.exception;

import io.netty.handler.codec.http.HttpResponseStatus;

public class InsuficientFundsException extends SimpleTransferException {
    public InsuficientFundsException() {
        super("The account has not enough funds for this transaction");
    }

    @Override
    public HttpResponseStatus getStatus() {
        return HttpResponseStatus.CONFLICT;
    }
}
