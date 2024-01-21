package com.interqu.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.interqu.InterquReleaseApplication;

@Controller
public class API {

	protected static final Logger logger = LoggerFactory.getLogger(API.class);
	
	@GetMapping("/test")
	@ResponseBody
	public String test() {
		return "Hello World!";
	}
	
}
