package com.interqu;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.mongodb.repository.config.EnableMongoRepositories;

@SpringBootApplication
@EnableMongoRepositories
public class InterquReleaseApplication {

	public static final String SITE_URL = "http://localhost:8888";
	
	private static final Logger logger = LoggerFactory.getLogger(InterquReleaseApplication.class);
	
	public static void main(String[] args) {
		logger.info("Starting interqu release server...");
		SpringApplication.run(InterquReleaseApplication.class, args);
	}

}
