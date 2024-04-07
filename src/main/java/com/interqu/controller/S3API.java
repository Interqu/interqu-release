package com.interqu.controller;

import java.net.URL;
import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.interqu.S3.S3Service;
import com.interqu.exceptions.QuestionNotFoundException;
import com.interqu.exceptions.UserNotFoundException;
import com.interqu.interviews.InterviewUtils;
import com.interqu.interviews.Result;
import com.interqu.interviews.Status;
import com.interqu.interviews.questions.Question;
import com.interqu.models.S3PresignedIObject;
import com.interqu.user.User;

@RestController
@RequestMapping("/api/s3")
public class S3API extends API{

    @Autowired
    private S3Service s3;
    
    @Autowired 
    private InterviewUtils interviewUtils;

    @GetMapping("/generate-interview-upload-presigned-url")
    public S3PresignedIObject generateVideoUploadPresignedUrl(@AuthenticationPrincipal UserDetails userDetails, @RequestParam("questionId") String questionId) throws UserNotFoundException, QuestionNotFoundException{
        // Getting user id
        String username = userDetails.getUsername();
        User user = userRepo.findByEmail(username);
        // Ensure user is not null
        if(user == null){
            throw new UserNotFoundException("No user record in database.");
        }

        // Ensure questionid is valid
        Question question = questionRepo.findByQuestionId(questionId);
        if(question == null){
            throw new QuestionNotFoundException("No question found in database with id: " + questionId);
        }

        // Generate Interview Id
        String interviewId = interviewUtils.generateVideoId();
    
        // Save Date
        Date now = new Date();// TODO use UTC Time
        
        // Generate video file name.
        String videoFileName = interviewUtils.generateVideoFileName(user.getId(), interviewId, questionId, now);

        // Create interview document & setting all important information
        Result tempResult = new Result();
        tempResult.setId(interviewId);
        tempResult.setFileId(videoFileName);
        tempResult.setTimestamp(now);
        tempResult.setUserId(user.getId());
        tempResult.setStatus(Status.SUBMITTED);
        // Upload to MongoDb
        irRepo.save(tempResult);

        // Generate Presigned Url.
        URL presigned = s3.generateInterviewPresignedUrl(videoFileName);
        return new S3PresignedIObject(presigned, videoFileName, interviewId);
    }
}
