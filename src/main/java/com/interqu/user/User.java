package com.interqu.user;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.UUID;

import javax.persistence.Column;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.ManyToMany;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.interqu.interviews.Interview;
import com.interqu.roles.Role;
import com.interqu.subscription.Subscription;

@Document("users")
public class User {
	
	@Id
	@Field("id")
	@JsonProperty("id")
	private String id;
	
	@Field("email")
	@JsonProperty("email")
	@Column(unique = true)
	private String email;
	
	@Field("first_name")
	@JsonProperty("first_name")
	private String firstName;
	
	@Field("last_name")
	@JsonProperty("last_name")
	private String lastName;
	
	@Field("password")
	@JsonProperty("password")
	private String password;
	
	@Field("verified")
	@JsonProperty("verified")
	private boolean verified;
	
	@Field("roles")
	@JsonProperty("roles")
	@ManyToMany(fetch = FetchType.EAGER)
    private Set<Role> roles;
	
	@Field("enabled")
	@JsonProperty("enabled")
	private boolean enabled;
	
	@Field("verification_code")
	@JsonProperty("verification_code")
	private String verificationCode;
	
	@Field("date_registered")
	@JsonProperty("date_registered")
	private Date dateRegistered;
	
	@Field("subscription")
	@JsonProperty("subscription")
	private Subscription subscription;
	
	@Field("last_login")
	@JsonProperty("last_login")
	private Date lastLogin;
	
	@Field("profile_picture")
	@JsonProperty("profile_picture")
	private byte[] profilePicture;

	public User(String id, String email, String firstName, String lastName, String password, boolean verified,
			Set<Role> roles, boolean enabled, String verificationCode, Date dateRegistered, Subscription subscription,
			Date lastLogin, byte[] profilePicture) {
		super();
		this.id = id;
		this.email = email;
		this.firstName = firstName;
		this.lastName = lastName;
		this.password = password;
		this.verified = verified;
		this.roles = roles;
		this.enabled = enabled;
		this.verificationCode = verificationCode;
		this.dateRegistered = dateRegistered;
		this.subscription = subscription;
		this.lastLogin = lastLogin;
		this.profilePicture = profilePicture;
	}
	

	public User() {
		
	}


	public String getId() {
		return id;
	}


	public void setId(String id) {
		this.id = id;
	}


	public String getEmail() {
		return email;
	}


	public void setEmail(String email) {
		this.email = email;
	}

	public String getFullName() {
		return firstName + " " + lastName;
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


	public String getPassword() {
		return password;
	}


	public void setPassword(String password) {
		this.password = password;
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
	
	public void login() {
		Date currentDate = new Date(System.currentTimeMillis());
		this.setLastLogin(currentDate);
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


	@Override
	public String toString() {
		return "User [id=" + id + ", email=" + email + ", firstName=" + firstName + ", lastName=" + lastName
				+ ", password=" + password + ", verified=" + verified + ", roles=" + roles + ", enabled=" + enabled
				+ ", verificationCode=" + verificationCode + ", dateRegistered=" + dateRegistered + ", subscription="
				+ subscription + ", lastLogin=" + lastLogin + ", profilePicture=" + Arrays.toString(profilePicture)
				+ "]";
	}
	
}
