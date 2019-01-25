package com.pedrohrr.simpletransfer.data.account;

import com.pedrohrr.simpletransfer.data.ValidatedData;
import lombok.Data;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

@Data
public class AccountCreate extends ValidatedData {

    @NotNull
    private Long client;
    @NotBlank
    private String currency;
    @NotBlank
    private String iban;

}
