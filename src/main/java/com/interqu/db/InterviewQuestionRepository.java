package com.interqu.db;

import java.util.List;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Service;

import com.interqu.interviews.questions.Question;

@Service
public interface InterviewQuestionRepository extends MongoRepository<Question, String> {

    public List<Question> findByPosition(String position);

    public List<Question> findByPositionAndQuestion(String position, String question);
    
    public List<Question> findByPositionContainingAndQuestionContaining(String position, String question);

    public List<Question> findByQuestionId(String questionId);
    
    public List<Question> findByQuestionContaining(String question);
}
