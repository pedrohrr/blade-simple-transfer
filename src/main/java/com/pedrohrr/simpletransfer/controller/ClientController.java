package com.pedrohrr.simpletransfer.controller;

import com.blade.ioc.annotation.Inject;
import com.blade.mvc.annotation.*;
import com.blade.mvc.ui.RestResponse;
import com.pedrohrr.simpletransfer.data.client.ClientCreate;
import com.pedrohrr.simpletransfer.data.client.ClientUpdate;
import com.pedrohrr.simpletransfer.exception.SimpleTransferException;
import com.pedrohrr.simpletransfer.facade.AccountFacade;
import com.pedrohrr.simpletransfer.facade.ClientFacade;
import com.pedrohrr.simpletransfer.facade.TransferFacade;

@Path(restful = true, value = "/client")
public class ClientController {

    @Inject
    private ClientFacade clientFacade;

    @Inject
    private AccountFacade accountFacade;

    @Inject
    private TransferFacade transferFacade;

    @PostRoute
    public RestResponse addClient(@BodyParam ClientCreate client) throws SimpleTransferException {
        return RestResponse.ok(clientFacade.create(client));
    }

    @PutRoute
    public RestResponse updateClient(@BodyParam ClientUpdate client) throws SimpleTransferException {
        clientFacade.update(client);
        return RestResponse.ok();
    }

    @DeleteRoute("/:clientId")
    public RestResponse deleteClient(@PathParam final Long clientId) throws SimpleTransferException {
        clientFacade.delete(clientId);
        return RestResponse.ok();
    }

    @GetRoute("/:clientId")
    public RestResponse getById(@PathParam Long clientId) throws SimpleTransferException {
        return RestResponse.ok(clientFacade.findById(clientId));
    }

    @GetRoute("/findByName")
    public RestResponse findByName(@Param String name) throws SimpleTransferException {
        return RestResponse.ok(clientFacade.findByName(name));
    }

    @GetRoute("/:clientId/account")
    public RestResponse findAccounts(@PathParam final Long clientId) throws SimpleTransferException {
        return RestResponse.ok(accountFacade.findByClientId(clientId));
    }

    @GetRoute("/:clientId/transfer")
    public RestResponse findTransfers(@PathParam final Long clientId) throws SimpleTransferException {
        return RestResponse.ok(transferFacade.findByClientId(clientId));
    }

}