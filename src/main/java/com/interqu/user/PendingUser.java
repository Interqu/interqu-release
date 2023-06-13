package com.interqu.user;

import java.util.Set;

import org.springframework.data.mongodb.core.mapping.Document;

import com.interqu.roles.Role;

@Document("pending_user")
public class PendingUser {

    private String registrationCode;
    private String email;
    private Set<Role> roles;
    private String fullName;

    public PendingUser(String email, String fullName, String registrationCode, Set<Role> roles) {
        this.email = email;
        this.fullName = fullName;
        this.registrationCode = registrationCode;
        this.roles = roles;
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

    public Set<Role> getRoles() {
        return roles;
    }

    public void setRoles(Set<Role> roles) {
        this.roles = roles;
    }

}
