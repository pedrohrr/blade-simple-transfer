package com.pedrohrr.simpletransfer.config;

import com.blade.ioc.annotation.Bean;
import com.blade.kit.JsonKit;
import com.blade.mvc.handler.DefaultExceptionHandler;
import com.blade.mvc.http.Request;
import com.blade.mvc.http.Response;
import com.blade.mvc.ui.RestResponse;
import com.pedrohrr.simpletransfer.exception.SimpleTransferException;
import lombok.extern.slf4j.Slf4j;

@Bean
@Slf4j
public class ExceptionHandler extends DefaultExceptionHandler {

    @Override
    public void handleException(Exception e, Request request, Response response) {
        log.info("Exception handler called");
        if (e instanceof SimpleTransferException) {
            final SimpleTransferException exception = (SimpleTransferException) e;
            response.status(exception.getStatus().code());
            response.body(JsonKit.toString(RestResponse.fail(exception.getStatus().code(), e.getMessage())));
        } else {
            super.handle(e);
        }
    }

}