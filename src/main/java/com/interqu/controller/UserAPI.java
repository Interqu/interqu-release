package com.interqu.controller;

import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.interqu.db.PendingUserRepository;
import com.interqu.db.RoleRepository;
import com.interqu.db.UserRepository;
import com.interqu.email.EmailSenderService;
import com.interqu.errors.ErrorReport;
import com.interqu.roles.Role;
import com.interqu.user.CustomUserDetailsService;
import com.interqu.user.PendingUser;
import com.interqu.user.User;

import io.netty.util.internal.SocketUtils;
import jakarta.servlet.http.HttpServletRequest;
import net.bytebuddy.utility.RandomString;

@Controller
@RequestMapping("/api/user/")
public class UserAPI {
    
	@Autowired
	private UserRepository userRepo;

	@Autowired
	private EmailSenderService emailSenderService;

	@Autowired
	private CustomUserDetailsService customUserDetailsService;

	@Autowired
	private RoleRepository roleRepo;

	@Autowired
	private PendingUserRepository pendingUserRepo;

	@PostMapping("authenticate")
	public ModelAndView authneticateUser(User user, HttpServletRequest request) {
		UserDetails UD = customUserDetailsService.loadUserByUsername(user.getEmail());
		if (UD != null) {
			BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
			String encodedPassword = passwordEncoder.encode(user.getPassword());
			if (UD.getPassword().equals(encodedPassword)) {
				return new ModelAndView("interview-selection");
			}
		}
		ModelAndView inccorectInfo = new ModelAndView("login");
		inccorectInfo.addObject("Error", "Invalid Login Credentials");
		return inccorectInfo;
	}
	@PostMapping("/register")
	@ResponseBody
	public String registerUser(@RequestBody User user, HttpServletRequest request) {
		try {
			// Check if Account already exist
			User checkUser = userRepo.findByEmail(user.getEmail());
			if (checkUser != null) {
				// ModelAndView modelAndView = new ModelAndView("/login");
				// modelAndView.addObject("Email Exists", "Email Already Exist!");
				// return modelAndView;
				return "email exists!";
			}
			// Encrypting password
			// BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
			BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
			String encodedPassword = passwordEncoder.encode(user.getPassword());
			user.setPassword(encodedPassword);
			user.setId(UUID.randomUUID().toString());
			//TODO implement role repo;
			user.addRole(roleRepo.findByName("ADMIN"));
			// Generating verification code TODO
			String randomCode = RandomString.make(64);
			user.setVerificationCode(randomCode);
			user.setVerified(false);
			userRepo.save(user);
			//Send Verification Email;
			emailSenderService.sendUserVerificationCode(user);
			// ModelAndView modelAndView = new ModelAndView("/registerSuccess");
			// return modelAndView;
			return "Success! Please check email: " + user.getEmail();

		} catch (Exception e) {
			ModelAndView modelAndView = new ModelAndView( "/login");
			// ErrorReport error = new ErrorReport("SignUp Error", "Register User", e.getMessage());
			// modelAndView.addObject("Unexpected Error", "An unexpected error has occured.");
			e.printStackTrace();
			return "Unexpected error has occrred";
		}
	}


}
