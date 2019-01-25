
package com.pedrohrr.simpletransfer.model;

import com.pedrohrr.simpletransfer.enumeration.AccountStatus;
import io.github.biezhi.anima.annotation.Table;

import java.math.BigDecimal;
import java.util.Objects;

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

    public String getCurrency() {
        return currency;
    }

    public void setCurrency(String currency) {
        this.currency = currency;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getClient() {
        return client;
    }

    public void setClient(Long client) {
        this.client = client;
    }

    public BigDecimal getBalance() {
        return balance;
    }

    public void setBalance(BigDecimal balance) {
        this.balance = balance;
    }

    public String getIban() {
        return iban;
    }

    public void setIban(String iban) {
        this.iban = iban;
    }

    public AccountStatus getStatus() {
        return status;
    }

    public void setStatus(AccountStatus status) {
        this.status = status;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Account account = (Account) o;
        return iban.equals(account.iban);
    }

    @Override
    public int hashCode() {
        return Objects.hash(iban);
    }
}
