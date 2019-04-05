package com.komepay.web.models;

import java.time.LocalDate;

public class User {
    private long id;
    private String username;
    private String password;
    private String secret;
    private int isemailverified;
    private int isphoneverified;
    private int status;
    private LocalDate date;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getSecret() {
        return secret;
    }

    public void setSecret(String secret) {
        this.secret = secret;
    }

    public int getIsemailverified() {
        return isemailverified;
    }

    public void setIsemailverified(int isemailverified) {
        this.isemailverified = isemailverified;
    }

    public int getIsphoneverified() {
        return isphoneverified;
    }

    public void setIsphoneverified(int isphoneverified) {
        this.isphoneverified = isphoneverified;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public LocalDate getDate() {
        return date;
    }

    public void setDate(LocalDate date) {
        this.date = date;
    }
}
