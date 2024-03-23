package com.interqu.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import com.interqu.db.InterviewQuestionRepository;
import com.interqu.db.InterviewResultRepository;
import com.interqu.db.InterviewResultService;
import com.interqu.db.UserRepository;
import com.interqu.db.UserRepositoryService;
import com.interqu.email.EmailSenderService;
import com.interqu.jwt.JwtUtil;
import com.interqu.process.FileService;
import com.interqu.user.CustomUserDetailsService;

@Controller
public class API {

	// logger
	protected static final Logger logger = LoggerFactory.getLogger(API.class);
	
	// repositories

    @Autowired
    protected InterviewQuestionRepository iqRepo;

    @Autowired
    protected InterviewQuestionRepository questionRepo;
    
    @Autowired
    protected InterviewResultRepository irRepo;

    @Autowired
    protected UserRepository userRepo;

    @Autowired
    protected FileService fileService;
    
	@Autowired
	protected JwtUtil jwtUtil;
	
	@Autowired
	protected EmailSenderService emailSenderService;

	@Autowired
	protected CustomUserDetailsService customUserDetailsService;
	
	@Autowired
	protected InterviewResultService irService;
	
	@Autowired 
	protected UserRepositoryService userService;
	
}
