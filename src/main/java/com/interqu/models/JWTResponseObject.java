package com.interqu.models;

public class JWTResponseObject {

	private String JWT;
	private String email;
	
	public JWTResponseObject(String JWT, String email) {
		this.JWT = JWT;
		this.email = email;
	}

	public String getJWT() {
		return JWT;
	}

	public void setJWT(String jWT) {
		JWT = jWT;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	@Override
	public String toString() {
		return "JWTResponseObject [JWT=" + JWT + ", email=" + email + "]";
	}

	

}
