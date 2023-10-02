package com.interqu.utils;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

import com.interqu.interviews.InterviewVideoData;
import com.interqu.interviews.questions.Question;
import com.interqu.user.User;


public class Utils {
    
     public static InterviewVideoData setInterviewVideoData(User user, Question question){
        String id = UUID.randomUUID().toString();
        String fileName = user.getId() + "DAL" + question.getQuestionId() + "DAL" + id + ".mp4";
        return new InterviewVideoData(id, question.getQuestionId(), user.getId(), new Date(), fileName);
    }

    public static Map<String, String> generateInterviewMetadata(User user, Question question){
        Map<String, String> metadata = new HashMap<>();
        metadata.put("User",user.getEmail());
        metadata.put("Question_Id",question.getQuestionId());
        metadata.put("Question_En", question.getQuestion());
        metadata.put("Position_En", question.getPosition());
        return metadata;
    }

}
