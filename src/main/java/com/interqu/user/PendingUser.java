package com.interqu.user;

import org.springframework.data.mongodb.core.mapping.Document;

@Document("pending-user")
public class PendingUser {

    String hash;
    String email;
    String fullName;

    public PendingUser(String email, String fullName, String hash) {
        this.email = email;
        this.fullName = fullName;
        this.hash = hash;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getFullName() {
        return fullName;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    public String gethash() {
        return hash;
    }

    public void sethash(String hash) {
        this.hash = hash;
    }

}
