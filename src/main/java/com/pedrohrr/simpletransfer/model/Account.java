
package com.pedrohrr.simpletransfer.model;

import com.pedrohrr.simpletransfer.enumeration.AccountStatus;
import io.github.biezhi.anima.annotation.Table;
import lombok.Data;

import java.math.BigDecimal;

@Data
@Table(name = "accounts")
public class Account extends AbstractModel {

    private Long id;
    private Long client;
    private String currency;
    private BigDecimal balance;
    private String iban;
    private AccountStatus status;

    public Account() {
        this.status = AccountStatus.ACTIVE;
        this.balance = BigDecimal.ZERO;
    }

}
