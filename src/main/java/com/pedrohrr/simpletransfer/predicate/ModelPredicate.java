package com.pedrohrr.simpletransfer.predicate;

import com.pedrohrr.simpletransfer.model.Account;
import com.pedrohrr.simpletransfer.model.Client;

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

}
