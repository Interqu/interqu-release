package com.interqu.interviews;

import java.util.Date;
import java.util.UUID;

import org.springframework.stereotype.Component;

@Component
public class InterviewUtils {
    
    public String generateVideoFileName(String userId, String interviewId, String questionId, Date time){
        // Format Date (replace space and colon with -)
        String nowString = time.toString().replace(" ", "-").replace(":", "-");
        return userId + "-" + interviewId + "-" + questionId + "-" + nowString + "-interqu-interview.mp4";
    }

    public String generateVideoId(){
        return UUID.randomUUID().toString();
    }

}
