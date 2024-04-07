package com.interqu.interviews;

import java.util.Date;
import java.util.UUID;

import org.springframework.stereotype.Component;

@Component
public class InterviewUtils {
    
    public String generateVideoFileName(String userId, String interviewId, String questionId, Date time){
        return userId + "-" + interviewId + "-" + questionId + "-" + time.toString() + "-INTERQU-VIDEO.mp4";
    }

    public String generateVideoId(){
        return UUID.randomUUID().toString();
    }

}
