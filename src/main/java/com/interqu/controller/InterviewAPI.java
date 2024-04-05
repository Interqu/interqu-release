package com.interqu.controller;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.interqu.interviews.Result;
import com.interqu.interviews.questions.Question;

@RestController
@RequestMapping("/api/interview")
public class InterviewAPI extends API{

    @PostMapping(value = "/getQuestions")
    public List<Question> getQuestionByPosition(@RequestBody Question questionQuery) throws Exception{
    	// Search by Id
    	if(questionQuery.getQuestionId() != null && !questionQuery.getQuestionId().isEmpty()) {
    		return iqRepo.findByQuestionId(questionQuery.getQuestionId());
    	}
    	
    	// Search by question and position
    	if(questionQuery.getPosition() != null && !questionQuery.getPosition().isEmpty() && questionQuery.getQuestion() != null && !questionQuery.getQuestion().isEmpty()) {
    		return iqRepo.findByPositionContainingAndQuestionContaining(questionQuery.getPosition(), questionQuery.getQuestion());
    	}
    	
    	// Search by position
    	if(questionQuery.getPosition() != null && !questionQuery.getPosition().isEmpty()) {
    		return iqRepo.findByPosition(questionQuery.getPosition());
    	}
    	
    	// Search by question
    	if(questionQuery.getQuestion() != null && !questionQuery.getQuestion().isEmpty()) {
    		return iqRepo.findByQuestionContaining(questionQuery.getQuestion());
    	}
    	// Default: return all questions if no query params were valid.
    	return iqRepo.findAll();
    }

    @PostMapping("/getInterviewResult")
    public ResponseEntity<?> getInterviewResult(@AuthenticationPrincipal UserDetails userDetails, @RequestBody(required = false) Result result) {
    	if(result != null) {
    		if(result.getId() != null && !result.getId().isEmpty()) {
    			return ResponseEntity.ok(irService.findInterviewResultsById(result.getId(), userDetails.getUsername()));
    		}
    	}
    	return ResponseEntity.ok(irService.findInterviewResultsByEmail(userDetails.getUsername()));
    }
}
