package com.interqu.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.interqu.db.InterviewResultRepository;
import com.interqu.interviews.Result;
import com.interqu.interviews.data.VisualData;
import com.interqu.process.ContentAnaylze;

import io.netty.handler.codec.http.HttpMethod;

@Controller
@RequestMapping("dev")
public class DevController {
    
    @Autowired
    ContentAnaylze contentAnalyzer;

    @Autowired
    InterviewResultRepository irr;

    @GetMapping("/testAWSEmotionFunction")
    public ResponseEntity testAWSEmotion(@RequestParam("fileName") String fileName){
        try{
            VisualData vd = contentAnalyzer.generateInterviewVisualAnalysis(fileName);
            return new ResponseEntity<VisualData>(vd, HttpStatusCode.valueOf(200));
        }catch(Exception e){
            e.printStackTrace();
            return new ResponseEntity<>(e, HttpStatusCode.valueOf(500));
        }
    }

    @PostMapping("/addInterviewResult")
    public ResponseEntity<String> addInterviewResult(@RequestBody Result interviewResult){
        if(interviewResult!= null){
            irr.save(interviewResult);
            return new ResponseEntity<String>("Success", null, 200);
        }
         return new ResponseEntity<String>("Error", null, 400);
    }

}
