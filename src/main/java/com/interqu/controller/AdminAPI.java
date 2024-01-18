package com.interqu.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.interqu.db.UserRepository;
import com.interqu.email.EmailSenderService;

@Controller
@RequestMapping("/api/admin")
public class AdminAPI extends API{

	@Autowired
	private UserRepository userRepo;

    @Autowired
	private EmailSenderService emailSenderService;

}
