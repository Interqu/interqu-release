package com.interqu.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.interqu.db.PendingUserRepository;
import com.interqu.db.UserRepository;
import com.interqu.email.EmailSenderService;
import com.interqu.errors.ErrorReport;
import com.interqu.survey.BetaTestUserAnswer;
import com.interqu.user.CustomUserDetailsService;
import com.interqu.user.PendingUser;
import com.interqu.user.User;
import com.interqu.utils.Constants;

import jakarta.servlet.http.HttpServletRequest;
import net.bytebuddy.utility.RandomString;

@Controller
public class UserController {

	private static final String PAGE_PATH = "";
	private static final String API_PATH = "api";
	private static final String URL_PATH = "";

	@Autowired
	private UserRepository userRepo;

	@Autowired
	private PendingUserRepository pendingUserRepo;

	@Autowired
	private CustomUserDetailsService customUserDetailsService;

	@Autowired
	private EmailSenderService emailSenderService;

	@Autowired
	private PendingUserRepository pendingUserRepository;

	@GetMapping(URL_PATH + "/login")
	public ModelAndView login() {
		return new ModelAndView(PAGE_PATH + "/login");
	}

	@PostMapping(API_PATH + "/api/register")
	@ResponseBody
	public ModelAndView registerUser(@RequestBody User user, HttpServletRequest request) {
		try {
			// Check if Account already exist
			User checkUser = userRepo.findByEmail(user.getEmail());
			if (checkUser != null) {
				ModelAndView modelAndView = new ModelAndView(PAGE_PATH + "/login");
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
			ModelAndView modelAndView = new ModelAndView(PAGE_PATH + "/login");
			ErrorReport error = new ErrorReport("SignUp Error", "Register User", e.getMessage());
			modelAndView.addObject("Unexpected Error", "An unexpected error has occured.");
			return modelAndView;
		}
	}

	@PostMapping("authenticate")
	public ModelAndView authneticateUser(@RequestBody User user, HttpServletRequest request) {
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

//	@GetMapping("dev/configure-account")
//	public ModelAndView configureUserAccount() {
//		// Look up id in database
//		PendingUser pendingUser = pendingUserRepository.findByEmail("99sad");
//		if (pendingUser == null) {
//			return new ModelAndView("configure-account");
//		}
//		return new ModelAndView("Error").addObject("Error", "This account has already been configured!");
//	}
//
//	@PostMapping("configure-account")
//	public ModelAndView configureUserAccount(@RequestBody User user, @RequestParam String id) {
//		// Delete pending user from pending db
//		PendingUser pendingUser = pendingUserRepository.findByEmail(id);
//		if (pendingUser != null) {
//			pendingUserRepository.delete(pendingUser);
//			userRepo.insert(user);
//			return new ModelAndView("login").addObject("Successful", "Successfully configured your account!");
//		}
//		return new ModelAndView("Error").addObject("Error", "This account has already been configured!");
//	}
	
// TODO make it only so my role can access this
	@PostMapping("/dev/addBetaUser")
	@ResponseBody
	public String addBetaUser(@RequestBody PendingUser pendingUser){
		try{
			if(pendingUser.getEmail()!=null && pendingUser.getFullName()!=null){
				String randomCode = RandomString.make(64);
				pendingUser.setRegistrationCode(randomCode);
				pendingUserRepo.insert(pendingUser);
				emailSenderService.sendTestUserRegistrationEmail(pendingUser);
				return "Email has been sent!";
			}
		}catch(Exception e){
			
		}
		return "An unexpected error has occred";
	}



	@GetMapping("/dev" + Constants.BETA_USER_REGISTER_URL + "{registrationCode}")
	public ModelAndView registerBetaUser(@PathVariable String registrationCode) {
		PendingUser pendingUser = pendingUserRepo.findByRegistrationCode(registrationCode);
		if(pendingUser!=null){
			ModelAndView mvc = new ModelAndView("configure-account");
			mvc.addObject("name", pendingUser.getFullName());
			mvc.addObject("email", pendingUser.getEmail());
			mvc.addObject("BetaTestUserAnswer", new BetaTestUserAnswer());
			return mvc;
		}
		return new ModelAndView("link-expired");
		
	}
	
}
