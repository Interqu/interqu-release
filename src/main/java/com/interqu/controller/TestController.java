package com.interqu.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.interqu.db.UserRepository;
import com.interqu.user.User;

@Controller
public class TestController {

	@Autowired
	private UserRepository userRepo;

	@GetMapping("/test")
	@ResponseBody
	public String test() {
		User user = new User("Derek", "Li", "rejie.li@gmail.com", "12345TestPassword");
		System.out.println(user);
		userRepo.save(user);
		return "recieved!";
	}

	@GetMapping("/test1")
	@ResponseBody
	public String test1() {
		// userRepo.save(new
		// User("Derek","Li","rejie.li@gmail.com","12345TestPassword"));
		return "recieved!";
	}

}
