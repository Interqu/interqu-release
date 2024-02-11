package com.interqu.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.interqu.interviews.InterviewVideoData;
import com.interqu.interviews.Position;
import com.interqu.interviews.Result;
import com.interqu.interviews.questions.Question;
import com.interqu.user.User;
import com.interqu.utils.Utils;

import jakarta.servlet.http.HttpServletRequest;

@RestController
@RequestMapping("/api/interview")
public class InterviewAPI extends API{

    @PostMapping("/getPositions")
    public List<Position> getPositions() {
        return positionRepo.findAll();
    }

    @PostMapping("/getQuestions")
    public List<Question> getQuestionByPosition(@RequestBody Question questionQuery) {
    	
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
    public List<Result> getInterviewResult(@AuthenticationPrincipal UserDetails userDetails, @RequestBody(required = false) Result result) {
    	return irService.findInterviewResultsByEmail(userDetails.getUsername());
    }
    
    @PostMapping("/uploadInterview")
    public ResponseEntity<?> processInterview(HttpServletRequest request, @RequestParam("video") MultipartFile file, @RequestParam("questionId") String questionId){
        InterviewVideoData ivd;
        User user = null;
        Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        String username = null;
        if (principal instanceof UserDetails) {
             username = ((UserDetails)principal).getUsername();
             user = userRepo.findByEmail(username);
        }
        try{
            if(user == null){
                return new ResponseEntity<>("You do not have access to this page!", HttpStatus.FORBIDDEN);
            }
            Question question = questionRepo.findByQuestionId(questionId).get(0);
            
            ivd= Utils.setInterviewVideoData(user, question);
            ivdRepo.insert(ivd);
        }catch(Exception e){
            return new ResponseEntity<>(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
        try{
           fileService.uploadFile(ivd.getFileName(), file);
            return new ResponseEntity<>("Success", HttpStatus.OK);
        }catch(Exception e){
            ivdRepo.delete(ivd);
            e.printStackTrace();
            return new ResponseEntity<>(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

}
