package com.pedrohrr.simpletransfer.data.account;

import com.pedrohrr.simpletransfer.data.ValidatedData;
import lombok.Data;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Positive;
import java.math.BigDecimal;

@Data
public class AccountDeposit extends ValidatedData {

    @NotNull
    private Long id;
    @Positive
    private BigDecimal amount;

}
