package com.interqu.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class InterviewController {

    private final String PAGE_PATH = "";
    private final String URL_PATH = "";

    // TODO disable all access feature
    @GetMapping("dev/interview")
    public ModelAndView interviewPractice() {
        return new ModelAndView(PAGE_PATH + "/interview-practice");
    }

    @GetMapping("dev/interview/result")
    public ModelAndView interviewResult() {
        return new ModelAndView(PAGE_PATH + "/interview-result");
    }

    // @GetMapping("dev/test")
    // @ResponseBody
    // public String test() {
    // return "Hi";
    // }
}
