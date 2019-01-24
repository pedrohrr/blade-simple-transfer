package com.pedrohrr.simpletransfer.predicate;

import com.pedrohrr.simpletransfer.model.Client;

import java.util.function.Predicate;

public final class ClientPredicate {

    public static final String MODEL = "client";
    public static final String PASSPORT = "passport";

    public static Predicate<Client> getNamePredicate(final String name) {
        return c -> c.getFirstname().contains(name) || c.getLastname().contains(name);
    }

    public static Predicate<Client> getPassportPredicate(final String passport) {
        return c -> c.getPassport().equals(passport);
    }

}
