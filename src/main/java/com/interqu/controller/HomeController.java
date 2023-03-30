package com.interqu.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class HomeController {

	private final String PAGE_PATH = "";
	private final String URL_PATH = "";

	@GetMapping(URL_PATH + "/")
	public ModelAndView home() {
		return new ModelAndView(PAGE_PATH + "/home");
	}

}
