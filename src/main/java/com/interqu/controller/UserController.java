package com.interqu.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.servlet.ModelAndView;

import com.interqu.db.PendingUserRepository;
import com.interqu.db.RoleRepository;
import com.interqu.db.UserRepository;
import com.interqu.roles.Role;
import com.interqu.survey.BetaTestUserAnswer;
import com.interqu.user.PendingUser;
import com.interqu.user.User;
import com.interqu.utils.Constants;

import jakarta.servlet.http.HttpServletRequest;

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
	private RoleRepository roleRepo;

	@GetMapping(URL_PATH + "/login")
	public ModelAndView login() {
		return new ModelAndView(PAGE_PATH + "login");
	}

	@GetMapping("/register" + Constants.USER_VERIFICATION_URL + "{verificationCode}")
	public ModelAndView verifyUser(@PathVariable String verificationCode) {
		User user = userRepo.findByVerificationCode(verificationCode);
		if (user != null && !user.isVerified()) {
			user.setVerified(true);
			userRepo.save(user);
			ModelAndView mvc = new ModelAndView("verification-success");
			return mvc;
		}
		return new ModelAndView("link-expired");
	}

	// @GetMapping("dev/configure-account")
	// public ModelAndView configureUserAccount() {
	// // Look up id in database
	// PendingUser pendingUser = pendingUserRepository.findByEmail("99sad");
	// if (pendingUser == null) {
	// return new ModelAndView("configure-account");
	// }
	// return new ModelAndView("Error").addObject("Error", "This account has already
	// been configured!");
	// }
	//
	// @PostMapping("configure-account")
	// public ModelAndView configureUserAccount(@RequestBody User user,
	// @RequestParam String id) {
	// // Delete pending user from pending db
	// PendingUser pendingUser = pendingUserRepository.findByEmail(id);
	// if (pendingUser != null) {
	// pendingUserRepository.delete(pendingUser);
	// userRepo.insert(user);
	// return new ModelAndView("login").addObject("Successful", "Successfully
	// configured your account!");
	// }
	// return new ModelAndView("Error").addObject("Error", "This account has already
	// been configured!");
	// }

	@GetMapping("/dev" + Constants.BETA_USER_REGISTER_URL + "{registrationCode}")
	public ModelAndView registerBetaUser(@PathVariable String registrationCode) {
		PendingUser pendingUser = pendingUserRepo.findByRegistrationCode(registrationCode);
		if (pendingUser != null) {
			ModelAndView mvc = new ModelAndView("configure-account");
			mvc.addObject("name", pendingUser.getFullName());
			mvc.addObject("email", pendingUser.getEmail());
			mvc.addObject("BetaTestUserAnswer", new BetaTestUserAnswer());
			return mvc;
		}
		return new ModelAndView("link-expired");

	}

	@GetMapping("/dev/createPendingUser")
	@PreAuthorize("hasRole('DEV') or hasRole('ADMIN')")
	public ModelAndView createUserPage(HttpServletRequest request) {
		// GET CURRENT ROLE OF LOGGED IN USER
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		// auth.getAuthorities()
		List<Role> roles = roleRepo.findAll();

		ModelAndView mvc = new ModelAndView("manual-user-creation");
		mvc.addObject("roles", roles);
		return mvc;
	}

	@GetMapping("dev/account-settings")
	public ModelAndView accountSettings() {
		return new ModelAndView("account-settings");
	}

	// @PostMapping("dev/createRole/{roleName}")
	// @ResponseBody
	// public String createRole(@PathVariable String roleName){
	// Role role = new Role(roleName);
	// roleRepo.save(role);
	// return "Successful";
	// }
}
