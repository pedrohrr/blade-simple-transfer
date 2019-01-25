package com.pedrohrr.simpletransfer.data.transfer;

import com.pedrohrr.simpletransfer.data.account.AccountMinimal;
import com.pedrohrr.simpletransfer.enumeration.TransferStatus;
import lombok.Data;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Data
public class TransferDetailed {
    private Long id;
    private BigDecimal amount;
    private String notes;
    private AccountMinimal receiver;
    private AccountMinimal sender;
    private LocalDateTime createdAt;
    private LocalDateTime processedAt;
    private BigDecimal conversion;
    private TransferStatus status;
}
