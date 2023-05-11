package com.interqu.db;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Service;

import com.interqu.interviews.questions.InterviewQuestions;

@Service
public interface InterviewQuestionRepository extends MongoRepository<InterviewQuestions, String>  {
    
    // public InterviewQuestions findByRole(String role);

}
