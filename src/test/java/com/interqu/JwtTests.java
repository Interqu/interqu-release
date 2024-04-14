package com.interqu;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.withinPercentage;

import java.util.Date;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.junit.jupiter.MockitoExtension;

import com.interqu.jwt.JwtUtil;

@ExtendWith(MockitoExtension.class)
public class JwtTests {

	private static final double EPSILON = 1e-4;
	private static final String EMAIL1 = "mark@gmail.com";
	private static final String EMAIL2 = "captincook@gmail.com";

	@InjectMocks
	private JwtUtil utils;
	
	@Test
	public void testJwtGeneration() {
		long startTime = System.currentTimeMillis();
		//Email 1
		String jwt1 = utils.generateToken(EMAIL1);

		assertThat(jwt1).isNotEqualTo(EMAIL1);

		String decryptedJwt = utils.extractUsername(jwt1);
		assertThat(decryptedJwt).isEqualTo(EMAIL1);

		//Verify time
		Date expiryDate = utils.extractExpiration(jwt1);
		assertThat(expiryDate.getTime()).isCloseTo(startTime,withinPercentage(1L));
		//Email 2
		String jwt2 = utils.generateToken(EMAIL2);

		assertThat(jwt2).isNotEqualTo(EMAIL2);

		String decryptedJwt2 = utils.extractUsername(jwt2);
		assertThat(decryptedJwt2).isEqualTo(EMAIL2);
		//Ensure they are not equal to email1's jwt (jwt will generated different based off of username and time)
		assertThat(jwt1).isNotEqualTo(jwt2);
		expiryDate = utils.extractExpiration(jwt1);
		assertThat(expiryDate.getTime()).isCloseTo(startTime,withinPercentage(1L));
	}

	//TODO
	@Test
	public void testJwtIsExpired(){

	}
	
}
