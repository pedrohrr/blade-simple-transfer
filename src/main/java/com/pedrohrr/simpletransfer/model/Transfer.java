
package com.pedrohrr.simpletransfer.model;

import com.pedrohrr.simpletransfer.enumeration.TransferStatus;
import io.github.biezhi.anima.annotation.Table;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.Objects;

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

    public LocalDateTime getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(LocalDateTime createdAt) {
        this.createdAt = createdAt;
    }

    public BigDecimal getAmount() {
        return amount;
    }

    public void setAmount(BigDecimal amount) {
        this.amount = amount;
    }

    public String getNotes() {
        return notes;
    }

    public void setNotes(String notes) {
        this.notes = notes;
    }

    public Long getReceiver() {
        return receiver;
    }

    public void setReceiver(Long receiver) {
        this.receiver = receiver;
    }

    public Long getSender() {
        return sender;
    }

    public void setSender(Long sender) {
        this.sender = sender;
    }

    public LocalDateTime getProcessedAt() {
        return processedAt;
    }

    public void setProcessedAt(LocalDateTime processedAt) {
        this.processedAt = processedAt;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public BigDecimal getConversion() {
        return conversion;
    }

    public void setConversion(BigDecimal conversion) {
        this.conversion = conversion;
    }

    public TransferStatus getStatus() {
        return status;
    }

    public void setStatus(TransferStatus status) {
        this.status = status;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Transfer transfer = (Transfer) o;
        return amount.equals(transfer.amount) &&
                receiver.equals(transfer.receiver) &&
                sender.equals(transfer.sender) &&
                createdAt.equals(transfer.createdAt);
    }

    @Override
    public int hashCode() {
        return Objects.hash(amount, receiver, sender, createdAt);
    }
}
