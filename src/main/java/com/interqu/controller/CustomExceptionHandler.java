package com.interqu.controller;

import java.io.UnsupportedEncodingException;

import org.apache.http.HttpStatus;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import com.interqu.InterquReleaseApplication;
import com.interqu.exceptions.IncorrectCredentialsException;
import com.interqu.exceptions.NoEmailOrPassException;
import com.interqu.exceptions.UserNotFoundException;
import com.interqu.exceptions.UserAlreadyRegisteredException;

import jakarta.mail.MessagingException;

@ControllerAdvice
public class CustomExceptionHandler {

	private static final Logger logger = LoggerFactory.getLogger(InterquReleaseApplication.class);
	
	@ExceptionHandler(Exception.class)
	public ResponseEntity<String> handleException(Exception e){
		e.printStackTrace();
		logger.error(e.getMessage());
		return ResponseEntity.status(HttpStatus.SC_INTERNAL_SERVER_ERROR).body("An unexpected error occurred.");
	}
	
	@ExceptionHandler(NoEmailOrPassException.class)
	public ResponseEntity<String> handleNoEmailOrPassException(NoEmailOrPassException e){
		logger.warn(e.getMessage());
		return ResponseEntity.status(HttpStatus.SC_BAD_REQUEST).body(e.getMessage());
	}
	
	@ExceptionHandler(UsernameNotFoundException.class)
	public ResponseEntity<String> handleUserNotFoundException(UsernameNotFoundException e){
		logger.warn(e.getMessage());
		return ResponseEntity.status(HttpStatus.SC_FORBIDDEN).body(e.getMessage());
	}
	
	@ExceptionHandler(UserAlreadyRegisteredException.class)
	public ResponseEntity<String> UserAlreadyRegisteredException(UserAlreadyRegisteredException e){
		logger.warn(e.getMessage());
		return ResponseEntity.status(HttpStatus.SC_BAD_REQUEST).body(e.getMessage());
	}
	
	@ExceptionHandler(UserNotFoundException.class)
	public ResponseEntity<String> handleUserNotFoundException(UserNotFoundException e){
		logger.warn(e.getMessage());
		return ResponseEntity.status(HttpStatus.SC_FORBIDDEN).body(e.getMessage());
	}
	
	@ExceptionHandler(IncorrectCredentialsException.class)
	public ResponseEntity<String> handleIncorrectCredentialsException(IncorrectCredentialsException e){
		logger.warn(e.getMessage());
		return ResponseEntity.status(HttpStatus.SC_FORBIDDEN).body(e.getMessage());
	}
	
	@ExceptionHandler(UnsupportedEncodingException.class)
	public ResponseEntity<String> handleUserUnsupportedEncodingException(UserNotFoundException e){
		logger.warn(e.getMessage());
		return ResponseEntity.status(HttpStatus.SC_BAD_REQUEST).body(e.getMessage());
	}
	
	@ExceptionHandler(MessagingException.class)
	public ResponseEntity<String> handleMessagingException(IncorrectCredentialsException e){
		logger.warn(e.getMessage());
		return ResponseEntity.status(HttpStatus.SC_INTERNAL_SERVER_ERROR).body(e.getMessage());
	}
	
}
