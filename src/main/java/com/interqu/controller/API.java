package com.interqu.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.interqu.InterquReleaseApplication;
import com.interqu.db.InterviewQuestionRepository;
import com.interqu.db.InterviewResultRepository;
import com.interqu.db.InterviewResultService;
import com.interqu.db.InterviewVideoDataRepository;
import com.interqu.db.PositionRepository;
import com.interqu.db.UserRepository;
import com.interqu.db.UserRepositoryService;
import com.interqu.email.EmailSenderService;
import com.interqu.jwt.JwtUtil;
import com.interqu.process.ContentAnaylze;
import com.interqu.process.FileService;
import com.interqu.user.CustomUserDetailsService;

@Controller
public class API {

	// logger
	protected static final Logger logger = LoggerFactory.getLogger(API.class);
	
	// repositories
    @Autowired
    protected PositionRepository positionRepo;

    @Autowired
    protected InterviewQuestionRepository iqRepo;

    @Autowired
    protected InterviewVideoDataRepository ivdRepo;

    @Autowired
    protected InterviewQuestionRepository questionRepo;
    
    @Autowired
    protected InterviewResultRepository irRepo;

    @Autowired
    protected UserRepository userRepo;

    @Autowired
    protected FileService fileService;
    
    @Autowired
    protected ContentAnaylze contentAnalyzer;
    
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
	
	@GetMapping("/test")
	@ResponseBody
	public String test() {
		return "Hello World!";
	}
	
}
