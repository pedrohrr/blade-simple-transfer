package com.pedrohrr.simpletransfer.data.account;

import com.pedrohrr.simpletransfer.enumeration.AccountStatus;
import lombok.Data;

import java.math.BigDecimal;

@Data
public class AccountDetailed extends AccountCreate {

    private Long id;
    private Long client;
    private String currency;
    private String iban;
    private BigDecimal balance;
    private AccountStatus status;

}
