package com.interqu.user;

import org.springframework.data.mongodb.core.mapping.Document;

@Document("pending-user")
public class PendingUser {

    String registrationCode;
    String email;
    String fullName;

    public PendingUser(String email, String fullName, String registrationCode) {
        this.email = email;
        this.fullName = fullName;
        this.registrationCode = registrationCode;
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

    public String getRegistrationCode() {
        return registrationCode;
    }

    public void setRegistrationCode(String registrationCode) {
        this.registrationCode = registrationCode;
    }

}
