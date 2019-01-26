package com.pedrohrr.simpletransfer.data.account;

import com.pedrohrr.simpletransfer.data.client.ClientMinimal;
import com.pedrohrr.simpletransfer.enumeration.AccountStatus;
import lombok.Data;

import java.math.BigDecimal;

@Data
public class AccountDetailed {

    private Long id;
    private ClientMinimal client;
    private String currency;
    private String iban;
    private BigDecimal balance;
    private AccountStatus status;

}
