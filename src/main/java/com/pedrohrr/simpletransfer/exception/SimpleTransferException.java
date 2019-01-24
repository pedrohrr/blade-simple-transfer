package com.pedrohrr.simpletransfer.exception;

import io.netty.handler.codec.http.HttpResponseStatus;

public abstract class SimpleTransferException extends Exception {
    public SimpleTransferException(String message) {
        super(message);
    }

    private class ExceptionJson {
        private Integer statusCode;
        private String message;

        public ExceptionJson(Integer statusCode, String message) {
            this.statusCode = statusCode;
            this.message = message;
        }

        public Integer getStatusCode() {
            return statusCode;
        }

        public String getMessage() {
            return message;
        }
    }

    public ExceptionJson jsonObject() {
        return new ExceptionJson(getStatus().code(), getMessage());
    }

    protected abstract HttpResponseStatus getStatus();
}
