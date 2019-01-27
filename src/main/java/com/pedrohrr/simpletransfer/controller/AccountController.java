package com.pedrohrr.simpletransfer.controller;

import com.blade.ioc.annotation.Inject;
import com.blade.mvc.annotation.*;
import com.blade.mvc.ui.RestResponse;
import com.pedrohrr.simpletransfer.data.account.AccountCreate;
import com.pedrohrr.simpletransfer.data.account.AccountDeposit;
import com.pedrohrr.simpletransfer.exception.SimpleTransferException;
import com.pedrohrr.simpletransfer.facade.AccountFacade;
import com.pedrohrr.simpletransfer.facade.TransferFacade;

@Path(restful = true, value = "/account")
public class AccountController {

    @Inject
    private AccountFacade accountFacade;

    @Inject
    private TransferFacade transferFacade;

    @PostRoute
    public RestResponse addAccount(@BodyParam final AccountCreate account) throws SimpleTransferException {
        return RestResponse.ok(accountFacade.create(account));
    }

    @PutRoute
    public RestResponse deposit(@BodyParam final AccountDeposit account) throws SimpleTransferException {
        accountFacade.deposit(account);
        return RestResponse.ok();
    }

    @GetRoute("/:accountId")
    public RestResponse getById(@PathParam final Long accountId) throws SimpleTransferException {
        return RestResponse.ok(accountFacade.findById(accountId));
    }

    @GetRoute("/:accountId/transfer")
    public RestResponse findTransfers(final Long accountId) throws SimpleTransferException {
        return RestResponse.ok(transferFacade.findByAccountId(accountId));
    }

}
