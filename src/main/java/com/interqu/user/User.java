package com.interqu.user;

import java.util.ArrayList;
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

import com.interqu.interviews.Interview;
import com.interqu.roles.Role;
import com.interqu.subscription.Subscription;

@Document("users")
public class User {
	@Id
	private String id;
	@Column(unique = true)
	private String email;
	private String firstName;
	private String lastName;
	private String password;
	private boolean verified;
	@ManyToMany(fetch = FetchType.EAGER)
    private Set<Role> roles;
	private boolean banned;
	private String verificationCode;
	private Date dateRegistered;
	private Subscription subscription;
	private List<Interview> previousInterviews= new ArrayList<Interview>();
	

	public User(String id, String email, String firstName, String lastName, String password, boolean verified, Set<Role> roles,
			boolean banned, Date dateRegistered, Subscription subscription, List<Interview> previousInterviews) {
		this.id = id;
				this.email = email;
		this.firstName = firstName;
		this.lastName = lastName;
		this.password = password;
		this.verified = verified;
		this.roles = roles;
		this.banned = banned;
		this.dateRegistered = dateRegistered;
		this.subscription = subscription;
		this.previousInterviews = previousInterviews;
	}

	public User(String firstName, String lastName, String email, String password) {
		this(UUID.randomUUID().toString(),firstName,lastName,email,password,false,new HashSet<>(),false,new Date(),new Subscription(Subscription.FREE,Subscription.MONTHY,12.99, new Date(), null),new ArrayList<Interview>());
	}

	public User(){
		
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
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
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
	public boolean isBanned() {
		return banned;
	}
	public void setBanned(boolean banned) {
		this.banned = banned;
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
	public List<Interview> getPreviousInterviews() {
		return previousInterviews;
	}
	public void setPreviousInterviews(List<Interview> previousInterviews) {
		this.previousInterviews = previousInterviews;
	}

	@Override
	public String toString() {
		return "User [firstName=" + firstName + ", lastName=" + lastName + ", email=" + email
				+ ", password=" + password + ", verified=" + verified + ", banned=" + banned + ", dateRegistered="
				+ dateRegistered + ", subscription=" + subscription + ", previousInterviews=" + previousInterviews
				+ "]";
	}
	public Set<Role> getRoles() {
		return roles;
	}
	public void setRoles(Set<Role> roles) {
		this.roles = roles;
	}

	public void addRole(Role role){
		if(roles==null){
			setRoles(new HashSet<Role>());
		}
		roles.add(role);
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getVerificationCode() {
		return verificationCode;
	}

	public void setVerificationCode(String verificationCode) {
		this.verificationCode = verificationCode;
	}
	
}
