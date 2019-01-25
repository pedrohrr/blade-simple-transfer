package com.pedrohrr.simpletransfer.data.transfer;

import com.pedrohrr.simpletransfer.data.ValidatedData;
import lombok.Data;
import lombok.NonNull;

import javax.validation.constraints.Positive;
import java.math.BigDecimal;

@Data
public class TransferCreate extends ValidatedData {

    @Positive
    private BigDecimal amount;
    @NonNull
    private Long receiver;
    @NonNull
    private Long sender;
    private String notes;

}
