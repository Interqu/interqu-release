package com.interqu.exceptions;

public class QuestionNotFoundException extends Exception{
    
    public QuestionNotFoundException() {
        super("Quesiton Not Found In Database.");
    }

    public QuestionNotFoundException(String message) {
        super(message);
    }

    public QuestionNotFoundException(String message, Throwable cause) {
        super(message, cause);
    }

    public QuestionNotFoundException(Throwable cause) {
        super(cause);
    }

}
