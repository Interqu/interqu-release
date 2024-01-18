package com.interqu.controller;

import org.apache.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import com.interqu.exceptions.IncorrectCredentialsException;
import com.interqu.exceptions.NoEmailOrPassException;
import com.interqu.exceptions.UserNotFoundException;

@ControllerAdvice
public class CustomExceptionHandler {

	@ExceptionHandler(Exception.class)
	public ResponseEntity<String> handleException(Exception e){
		//TODO log exception
		e.printStackTrace();
		return ResponseEntity.status(HttpStatus.SC_INTERNAL_SERVER_ERROR).body("An unexpected error occurred.");
	}
	
	@ExceptionHandler(NoEmailOrPassException.class)
	public ResponseEntity<String> handleNoEmailOrPassException(NoEmailOrPassException e){
		//TODO log exception
		return ResponseEntity.status(HttpStatus.SC_BAD_REQUEST).body(e.getMessage());
	}
	
	@ExceptionHandler(UserNotFoundException.class)
	public ResponseEntity<String> handleUserNotFoundException(UserNotFoundException e){
		//TODO log exception
		return ResponseEntity.status(HttpStatus.SC_FORBIDDEN).body(e.getMessage());
	}
	
	@ExceptionHandler(IncorrectCredentialsException.class)
	public ResponseEntity<String> handleIncorrectCredentialsException(IncorrectCredentialsException e){
		//TODO log exception
		return ResponseEntity.status(HttpStatus.SC_FORBIDDEN).body(e.getMessage());
	}
	
}
