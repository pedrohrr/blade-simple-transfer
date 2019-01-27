package com.pedrohrr.simpletransfer.predicate;

import com.pedrohrr.simpletransfer.enumeration.TransferStatus;
import com.pedrohrr.simpletransfer.model.Account;
import com.pedrohrr.simpletransfer.model.Client;
import com.pedrohrr.simpletransfer.model.Transfer;

import java.util.function.Predicate;

public final class ModelPredicate {

    public static final String PASSPORT = "passport";
    public static final String IBAN = "iban";

    public static Predicate<Client> getNamePredicate(final String name) {
        return c -> c.getFirstname().contains(name) || c.getLastname().contains(name);
    }

    public static Predicate<Client> getPassportPredicate(final String passport) {
        return c -> c.getPassport().equals(passport);
    }

    public static Predicate<Account> getIbanPredicate(Account account) {
        return a -> a.getIban().equals(account.getIban());
    }

    public static Predicate<Account> getClientPredicate(Long id) {
        return a -> a.getClient().equals(id);
    }

    public static Predicate<Transfer> getSenderIdPredicate(Long id) {
        return a -> a.getSender().equals(id);
    }

    public static Predicate<Transfer> getReceiverIdPredicate(Long id) {
        return a -> a.getReceiver().equals(id);
    }

    public static Predicate<Transfer> getStatusPredicate(TransferStatus status) {
        return t -> t.getStatus().equals(status);
    }

}
