package com.interqu.db;

import java.util.List;

import org.springframework.data.mongodb.repository.MongoRepository;

import com.interqu.interviews.InterviewVideoData;

public interface InterviewVideoDataRepository extends MongoRepository<InterviewVideoData, String>{
    
    List<InterviewVideoData> findByUserId(String userId);
    
    InterviewVideoData findInterviewVideoDataById(String id);



}
