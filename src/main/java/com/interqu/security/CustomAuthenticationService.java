package com.interqu.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

// @Service
public class CustomAuthenticationService {

    // @Autowired
    // private AuthenticationManager authenticationManager;

    // public boolean authenticateUser(String username, String password) {
    //     try {
    //         // Create an authentication token with the user's credentials
    //         Authentication authentication = authenticationManager.authenticate(
    //                 new UsernamePasswordAuthenticationToken(username, password));
    //          SecurityContextHolder.getContext().setAuthentication(authentication);
    //         // Authentication successful
    //         return authentication.isAuthenticated();
    //     } catch (Exception e) {
    //         // Authentication failed
    //         return false;
    //     }
    // }
}
