package com.interqu.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class API {

	@GetMapping("/test")
	@ResponseBody
	public String test() {
		return "Hello World!";
	}
	
}
