package com.interqu.controller;

import java.io.UnsupportedEncodingException;
import java.util.Date;
import java.util.UUID;

import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.interqu.exceptions.IncorrectCredentialsException;
import com.interqu.exceptions.NoEmailOrPassException;
import com.interqu.exceptions.UserAlreadyRegisteredException;
import com.interqu.exceptions.UserNotFoundException;
import com.interqu.exceptions.UserNotLoggedIn;
import com.interqu.models.JWTResponseObject;
import com.interqu.models.UserInfoResponseObject;
import com.interqu.user.CustomUserDetails;
import com.interqu.user.User;

import jakarta.mail.MessagingException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import net.bytebuddy.utility.RandomString;

@RestController
@RequestMapping("/api/user/")
public class UserAPI extends API{

	@PostMapping("authenticate")
	public ResponseEntity<JWTResponseObject> authneticateUser(@RequestBody User user, HttpServletRequest request) throws NoEmailOrPassException, UserNotFoundException,UsernameNotFoundException, IncorrectCredentialsException {
		logger.info("/authenticate: " + user.getEmail() + " is trying to authenticate.");
		//Ensure provided user has an email and password
		if(user.getEmail() == null || user.getPassword() == null) {
			throw new NoEmailOrPassException();
		}
		//Find user
		UserDetails UD = customUserDetailsService.loadUserByUsername(user.getEmail());
		if(UD == null) {
			throw new UserNotFoundException("User " + user.getEmail() + " was not found.");
		}
		//Check if password match
		BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
		boolean passwordMatch = passwordEncoder.matches(user.getPassword(), UD.getPassword());
		if (!passwordMatch) {
			throw new IncorrectCredentialsException();
		}
		
		//generate JWT
		String jwt = jwtUtil.generateToken(user.getEmail());
		
		//preparing return obj
		JWTResponseObject jwtObj = new JWTResponseObject(jwt, user.getEmail());
		logger.info("/authenticate: successfully authenticated: " + user.getEmail());
		return ResponseEntity.ok(jwtObj);
	}
	
	@GetMapping("getInfo")
	public ResponseEntity<UserInfoResponseObject> getUserInfo(HttpServletRequest request, HttpServletResponse response) throws Exception{
		logger.info("/getInfo: " + " getting user info.");
		//Assert there is a logged in user - sainity check
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        if (authentication == null || !authentication.isAuthenticated() || !(authentication.getPrincipal() instanceof CustomUserDetails)) {
            throw new UserNotLoggedIn();
        }
        
        //getting user details
        CustomUserDetails userDetails = (CustomUserDetails) authentication.getPrincipal();
        String username = userDetails.getUsername();
        String name = userDetails.getName();
        logger.info("/getInfo: successfully retrived user data: " + username);
        return ResponseEntity.ok(new UserInfoResponseObject(username, name));
	}
	
	
	@PostMapping("/register")
	@ResponseBody
	public ResponseEntity<String> registerUser(@RequestBody User user, HttpServletRequest request) throws NoEmailOrPassException, UserAlreadyRegisteredException, UnsupportedEncodingException, MessagingException {
		logger.info("/register: " + " registering " + user.getEmail());
		//Ensure provided user has an email and password
		if(user.getEmail() == null || user.getPassword() == null) {
			throw new NoEmailOrPassException();
		}
		//See if user is already registered
		UserDetails UD = customUserDetailsService.loadUserByUsername(user.getEmail());
		if(UD != null) {
			throw new UserAlreadyRegisteredException("User " + user.getEmail() + " is already registered");
		}
		
		//Encrypting password
		BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
		String encodedPassword = passwordEncoder.encode(user.getPassword());
		user.setPassword(encodedPassword);
		
		//Set other params
		user.setId(UUID.randomUUID().toString());
		user.setDateRegistered(new Date(System.currentTimeMillis()));
		//TODO Roles
		//user.addRole(Role.USER);
		
		//generate verification code
		String randomCode = RandomString.make(64);
		user.setVerificationCode(randomCode);
		user.setVerified(false);
		userRepo.save(user);
		
		//send email
		emailSenderService.sendUserVerificationCode(user);
		logger.info("/register: successfully registered user: " + user.getEmail());
		return ResponseEntity.ok("User Registered Successfully. Verfication Email Sent.");
	}

}
