package com.interqu.models;

import java.util.Date;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.FetchType;
import javax.persistence.ManyToMany;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Field;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.interqu.roles.Role;
import com.interqu.subscription.Subscription;
import com.interqu.user.User;

public class UserInfoResponseObject {
	
	private String email;
	
	private String firstName;
	
	private String lastName;
	
	private boolean verified;
	
    private Set<Role> roles;
	
	private boolean enabled;
	
	private String verificationCode;
	
	private Date dateRegistered;

	private Subscription subscription;
	
	private Date lastLogin;
	
	private byte[] profilePicture;
	
	public UserInfoResponseObject(User user) {
		this.email = user.getEmail();
		this.firstName = user.getFirstName();
		this.lastName = user.getLastName();
		this.verified = user.isVerified();
		this.roles = user.getRoles();
		this.enabled = user.isEnabled();
		this.verificationCode = user.getVerificationCode();
		this.dateRegistered = user.getDateRegistered();
		this.subscription = user.getSubscription();
		this.lastLogin = user.getLastLogin();
		this.profilePicture = user.getProfilePicture();
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
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

	public boolean isVerified() {
		return verified;
	}

	public void setVerified(boolean verified) {
		this.verified = verified;
	}

	public Set<Role> getRoles() {
		return roles;
	}

	public void setRoles(Set<Role> roles) {
		this.roles = roles;
	}

	public boolean isEnabled() {
		return enabled;
	}

	public void setEnabled(boolean enabled) {
		this.enabled = enabled;
	}

	public String getVerificationCode() {
		return verificationCode;
	}

	public void setVerificationCode(String verificationCode) {
		this.verificationCode = verificationCode;
	}

	public Date getDateRegistered() {
		return dateRegistered;
	}

	public void setDateRegistered(Date dateRegistered) {
		this.dateRegistered = dateRegistered;
	}

	public Subscription getSubscription() {
		return subscription;
	}

	public void setSubscription(Subscription subscription) {
		this.subscription = subscription;
	}

	public Date getLastLogin() {
		return lastLogin;
	}

	public void setLastLogin(Date lastLogin) {
		this.lastLogin = lastLogin;
	}

	public byte[] getProfilePicture() {
		return profilePicture;
	}

	public void setProfilePicture(byte[] profilePicture) {
		this.profilePicture = profilePicture;
	}
	
	
	
}
