
package com.pedrohrr.simpletransfer.model;

import io.github.biezhi.anima.annotation.Table;

import java.util.Objects;

@Table(name = "clients")
public class Client extends AbstractModel {

    private Long id;
    private String firstname;
    private String passport;
    private String lastname;

    public String getFirstname() {
        return firstname;
    }

    public void setFirstname(String firstname) {
        this.firstname = firstname;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getPassport() {
        return passport;
    }

    public void setPassport(String passport) {
        this.passport = passport;
    }

    public String getLastname() {
        return lastname;
    }

    public void setLastname(String lastname) {
        this.lastname = lastname;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Client client = (Client) o;
        return passport.equals(client.passport);
    }

    @Override
    public int hashCode() {
        return Objects.hash(passport);
    }
}
