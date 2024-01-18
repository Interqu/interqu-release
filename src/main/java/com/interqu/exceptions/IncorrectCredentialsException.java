package com.interqu.exceptions;

@SuppressWarnings("serial")
public class IncorrectCredentialsException extends Exception {

    public IncorrectCredentialsException() {
        super("Incorrect credentials provided");
    }

    public IncorrectCredentialsException(String message) {
        super(message);
    }

    public IncorrectCredentialsException(String message, Throwable cause) {
        super(message, cause);
    }

    public IncorrectCredentialsException(Throwable cause) {
        super(cause);
    }
}
