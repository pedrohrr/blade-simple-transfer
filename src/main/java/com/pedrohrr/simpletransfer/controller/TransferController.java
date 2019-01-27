package com.pedrohrr.simpletransfer.controller;

import com.blade.ioc.annotation.Inject;
import com.blade.mvc.annotation.*;
import com.blade.mvc.ui.RestResponse;
import com.pedrohrr.simpletransfer.data.transfer.TransferCreate;
import com.pedrohrr.simpletransfer.exception.SimpleTransferException;
import com.pedrohrr.simpletransfer.facade.TransferFacade;

@Path(restful = true, value = "/transfer")
public class TransferController {

    @Inject
    private TransferFacade facade;

    @PostRoute
    public RestResponse create(@BodyParam final TransferCreate transfer) throws SimpleTransferException {
        return RestResponse.ok(facade.create(transfer));
    }

    @GetRoute("/:transferId")
    public RestResponse findById(@PathParam final Long transferId) throws SimpleTransferException {
        return RestResponse.ok(facade.findById(transferId));
    }

}
