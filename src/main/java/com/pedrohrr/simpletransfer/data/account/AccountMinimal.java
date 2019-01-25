package com.pedrohrr.simpletransfer.data.account;

import com.pedrohrr.simpletransfer.data.client.ClientMinimal;
import lombok.Data;

@Data
public class AccountMinimal {

    private String iban;
    private ClientMinimal client;

}
