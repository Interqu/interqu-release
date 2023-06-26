package com.interqu.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.interqu.db.PendingUserRepository;
import com.interqu.db.UserRepository;
import com.interqu.email.EmailSenderService;
import com.interqu.user.PendingUser;
import com.interqu.user.User;

import net.bytebuddy.utility.RandomString;

@Controller
@RequestMapping("/api/admin")
public class AdminAPI {

	@Autowired
	private PendingUserRepository pendingUserRepo;

	@Autowired
	private UserRepository userRepo;

    @Autowired
	private EmailSenderService emailSenderService;

    	@PostMapping("/createPendingUser")
	public ModelAndView createPendingUser(PendingUser pendingUser){
			ModelAndView mvc = new ModelAndView("manual-user-creation");
		try{
				if(pendingUser.getEmail()!=null && pendingUser.getFirstName()!=null){
					//Check if pending user exists or not
					PendingUser pendingUserCheck = pendingUserRepo.findByEmail(pendingUser.getEmail());
					if(pendingUserCheck!=null){
						mvc.addObject("message", "Pending User already exists!");
						return mvc;
					}
					User userCheck = userRepo.findByEmail(pendingUser.getEmail());
					if(userCheck!=null){
						mvc.addObject("message", "User already exists!");
						return mvc;
					}
					String randomCode = RandomString.make(64);
					pendingUser.setRegistrationCode(randomCode);
					pendingUserRepo.insert(pendingUser);
					emailSenderService.sendTestUserRegistrationEmail(pendingUser);
					mvc.addObject("message", "Success! Please check: " + pendingUser.getEmail());
				}
		}catch(Exception e){
			mvc.addObject("error", "Unexpected error: " + e.getMessage());
		}
		return mvc;
	}
}
