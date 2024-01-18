package com.interqu.exceptions;

public class UserAlreadyRegisteredException extends Exception{

    public UserAlreadyRegisteredException() {
        super("User already exists.");
    }

    public UserAlreadyRegisteredException(String message) {
        super(message);
    }

    public UserAlreadyRegisteredException(String message, Throwable cause) {
        super(message, cause);
    }

    public UserAlreadyRegisteredException(Throwable cause) {
        super(cause);
    }
	
}
