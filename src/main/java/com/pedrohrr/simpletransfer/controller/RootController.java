package com.pedrohrr.simpletransfer.controller;

import com.blade.mvc.annotation.GetRoute;
import com.blade.mvc.annotation.Path;
import com.pedrohrr.simpletransfer.exception.SimpleTransferException;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Path
public class RootController {

    @GetRoute
    public String schema() throws SimpleTransferException {
        return "schema.json";
    }

}
