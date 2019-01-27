package com.pedrohrr.simpletransfer.data.transfer;

import com.pedrohrr.simpletransfer.data.account.AccountMinimal;
import com.pedrohrr.simpletransfer.enumeration.TransferStatus;
import lombok.Data;

import java.math.BigDecimal;

@Data
public class TransferMinimal {

    private BigDecimal amount;
    private AccountMinimal receiver;
    private AccountMinimal sender;
    private TransferStatus status;

}
