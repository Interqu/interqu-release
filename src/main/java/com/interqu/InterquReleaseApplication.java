package com.interqu;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.mongodb.repository.config.EnableMongoRepositories;

@SpringBootApplication
@EnableMongoRepositories
public class InterquReleaseApplication {

	public static final String SITE_URL = "http://localhost:8080";
	public static final String BETA_USER_REGISTER_URL = "/betaRegistration?code=";
	
	public static void main(String[] args) {
		SpringApplication.run(InterquReleaseApplication.class, args);
	}

}
