package com.interqu.db;

import java.util.List;

import org.springframework.data.mongodb.repository.MongoRepository;

import com.interqu.interviews.Result;

public interface InterviewResultRepository extends MongoRepository<Result, String>{
    
    public List<Result> findByEmail(String email);

}
