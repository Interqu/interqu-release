package com.interqu.db;

import java.util.List;
import java.util.Optional;

import org.springframework.data.mongodb.repository.MongoRepository;

import com.interqu.interviews.Result;

public interface InterviewResultRepository extends MongoRepository<Result, String>{
    
	public Optional<Result> findById(String id);
	
//    public List<Result> findByEmail(String email);

}
