package com.interqu.exceptions;

@SuppressWarnings("serial")
public class UserNotLoggedIn  extends Exception {
	
    public UserNotLoggedIn() {
        super("User not logged in");
    }

    public UserNotLoggedIn(String message) {
        super(message);
    }

    public UserNotLoggedIn(String message, Throwable cause) {
        super(message, cause);
    }

    public UserNotLoggedIn(Throwable cause) {
        super(cause);
    }
    
}
