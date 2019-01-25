
package com.pedrohrr.simpletransfer.model;

import com.pedrohrr.simpletransfer.enumeration.TransferStatus;
import io.github.biezhi.anima.annotation.Table;
import lombok.Data;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Data
@Table(name = "transfers")
public class Transfer extends AbstractModel {

    private Long id;
    private BigDecimal amount;
    private String notes;
    private Long receiver;
    private Long sender;
    private LocalDateTime createdAt;
    private LocalDateTime processedAt;
    private BigDecimal conversion;
    private TransferStatus status;

    public Transfer() {
        this.status = TransferStatus.POSTED;
        this.createdAt = LocalDateTime.now();
    }

    @Override
    public int update() {
        this.processedAt = LocalDateTime.now();

        return super.update();
    }

}
