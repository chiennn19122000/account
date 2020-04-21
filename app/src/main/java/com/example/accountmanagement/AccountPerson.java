package com.example.accountmanagement;

import androidx.annotation.NonNull;

public class AccountPerson {
    String accounttype;
    String user;
    String pass;

    public String getAccounttype() {
        return accounttype;
    }

    public void setAccounttype(String accounttype) {
        this.accounttype = accounttype;
    }

    public String getUser() {
        return user;
    }

    public void setUser(String user) {
        this.user = user;
    }

    public String getPass() {
        return pass;
    }

    public void setPass(String pass) {
        this.pass = pass;
    }

    public AccountPerson(String accounttype, String user, String pass) {
        this.accounttype = accounttype;
        this.user = user;
        this.pass = pass;
    }

    @NonNull
    @Override
    public String toString() {
        return getAccounttype() + "\n" +"user: " +getUser() + "\n" + "pass: " + getPass() ;
    }
}


