package com.pedrohrr.simpletransfer.data.transfer;

import com.pedrohrr.simpletransfer.data.account.AccountMinimal;
import lombok.Data;

import java.math.BigDecimal;

@Data
public class TransferMinimal {

    private BigDecimal amount;
    private AccountMinimal receiver;
    private AccountMinimal sender;

}
