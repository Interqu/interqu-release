package com.interqu.user;

import org.springframework.data.mongodb.core.mapping.Document;

@Document("beta_user")
public class BetaUser {

	private String email;
	private String firstName;
	private String lastName;
	private String registrationCode;
	
	public BetaUser(String email, String firstName, String lastName, String registrationCode) {
		super();
		this.email = email;
		this.firstName = firstName;
		this.lastName = lastName;
		this.registrationCode = registrationCode;
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
	public String getRegistrationCode() {
		return registrationCode;
	}
	public void setRegistrationCode(String registrationCode) {
		this.registrationCode = registrationCode;
	}
	
}
