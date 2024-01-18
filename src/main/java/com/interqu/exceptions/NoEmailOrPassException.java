package com.interqu.exceptions;

@SuppressWarnings("serial")
public class NoEmailOrPassException extends Exception {

    public NoEmailOrPassException() {
        super("Email or password is missing");
    }

    public NoEmailOrPassException(String message) {
        super(message);
    }

    public NoEmailOrPassException(String message, Throwable cause) {
        super(message, cause);
    }

    public NoEmailOrPassException(Throwable cause) {
        super(cause);
    }
}
