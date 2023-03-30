package com.interqu.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.interqu.db.UserRepository;
import com.interqu.errors.ErrorReport;
import com.interqu.user.User;

import jakarta.servlet.http.HttpServletRequest;

@Controller
public class UserController {

	private final String PAGE_PATH = "";
	private final String API_PATH = "api";
	private final String URL_PATH = "";

	@Autowired
	private UserRepository userRepo;

	@GetMapping(URL_PATH + "/login")
	public ModelAndView login() {
		return new ModelAndView(PAGE_PATH + "/login");
	}

	@GetMapping(URL_PATH + "/register")
	public ModelAndView register() {
		return new ModelAndView(PAGE_PATH + "/register");
	}

	@PostMapping(API_PATH + "/api")
	@ResponseBody
	public ModelAndView registerUser(@RequestBody User user, HttpServletRequest request) {
		try {
			// Check if Account already exist
			User checkUser = userRepo.findByEmail(user.getEmail());
			if (checkUser != null) {
				ModelAndView modelAndView = new ModelAndView(PAGE_PATH + "/signUp");
				modelAndView.addObject("Email Exists", "Email Already Exist!");
				return modelAndView;
			}
			// Encrypting password
			BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
			String encodedPassword = passwordEncoder.encode(user.getPassword());
			user.setPassword(encodedPassword);
			// Generating verification code TODO
			// String randomCode = RandomString.make(64);
			// user.setVerificationCode(randomCode);
			// user.setEnabled(false);
			userRepo.save(user);
			ModelAndView modelAndView = new ModelAndView(PAGE_PATH + "/registerSuccess");
			return modelAndView;

		} catch (Exception e) {
			ModelAndView modelAndView = new ModelAndView(PAGE_PATH + "/signUp");
			ErrorReport error = new ErrorReport("SignUp Error", "Register User", e.getMessage());
			modelAndView.addObject("Unexpected Error", "An unexpected error has occured.");
			return modelAndView;
		}
	}

}
