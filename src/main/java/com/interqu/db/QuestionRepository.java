package com.interqu.db;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Service;

import com.interqu.interviews.questions.InterviewQuestions;

@Service
public interface QuestionRepository extends MongoRepository<InterviewQuestions, String>{

	InterviewQuestions findByJobPosition(String jobPosition);
	
}
