package com.interqu.user;

import java.util.Set;

import org.springframework.data.mongodb.core.mapping.Document;

import com.interqu.roles.Role;

@Document("pending_user")
public class PendingUser {

    private String registrationCode;
    private String email;
    private Set<Role> roles;
    private String firstName;
    private String lastName;

    public PendingUser(){

    }

    public PendingUser(String registrationCode, String email, Set<Role> roles, String firstName, String lastName) {
        this.registrationCode = registrationCode;
        this.email = email;
        this.roles = roles;
        this.firstName = firstName;
        this.lastName = lastName;
    }
    public String getRegistrationCode() {
        return registrationCode;
    }
    public void setRegistrationCode(String registrationCode) {
        this.registrationCode = registrationCode;
    }
    public String getEmail() {
        return email;
    }
    public void setEmail(String email) {
        this.email = email;
    }
    public Set<Role> getRoles() {
        return roles;
    }
    public void setRoles(Set<Role> roles) {
        this.roles = roles;
    }
    public String getFirstName() {
        return firstName;
    }
    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }
    public String getLastName() {
        return lastName;
    }
    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getFullName(){
        return getFirstName() + " " + getLastName();
    }



}
