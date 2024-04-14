package com.interqu.controller;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.apache.catalina.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.interqu.S3.S3Service;
import com.interqu.interviews.Result;
import com.interqu.interviews.questions.Question;
import com.interqu.user.CustomUserDetails;

@RestController
@RequestMapping("/api/interview")
public class InterviewAPI extends API{

	@Autowired
	private S3Service s3;

    @PostMapping(value = "/getQuestions")
    public List<Question> getQuestionByPosition(@RequestBody Question questionQuery) throws Exception{
    	// Search by Id
    	if(questionQuery.getQuestionId() != null && !questionQuery.getQuestionId().isEmpty()) {
    		return new ArrayList<>(Arrays.asList(iqRepo.findByQuestionId(questionQuery.getQuestionId())));
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
    public ResponseEntity<?> getInterviewResult(@AuthenticationPrincipal CustomUserDetails userDetails, @RequestBody(required = false) Result result) {
    	if(result != null) {
    		if(result.getInterviewId() != null && !result.getInterviewId().isEmpty()) {
				result = irService.findInterviewResultsById(result.getInterviewId(), userDetails.getUsername());
				// Ensure current user is authorized to access this video.
				if(!result.getUserId().equals(userDetails.getUser().getId())){
					ResponseEntity.status(403).build();
				}
				// Replace file_id with presigned_url 
				String interviewAcccessUrl = s3.generateInterviewAccessUrl(result.getFileId()).toString();
				result.setFileId(interviewAcccessUrl);
    			return ResponseEntity.ok(result);
    		}
    	}
    	return ResponseEntity.ok(irService.findInterviewResultsByEmail(userDetails.getUsername()));
    }
}
