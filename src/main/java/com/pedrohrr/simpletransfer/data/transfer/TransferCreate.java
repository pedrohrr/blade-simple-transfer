package com.pedrohrr.simpletransfer.data.transfer;

import com.pedrohrr.simpletransfer.data.ValidatedData;
import lombok.Data;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Positive;
import java.math.BigDecimal;

@Data
public class TransferCreate extends ValidatedData {

    @NotNull
    @Positive
    private BigDecimal amount;
    @NotNull
    @Positive
    private Long receiver;
    @NotNull
    @Positive
    private Long sender;

}
