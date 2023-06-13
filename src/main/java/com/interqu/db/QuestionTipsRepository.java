package com.interqu.db;

import java.util.List;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Service;

import com.interqu.interviews.questions.QuestionTips;

@Service
public interface QuestionTipsRepository extends MongoRepository<QuestionTips, String> {

    List<QuestionTips> findByQuestion(String question);
    
    QuestionTips findByQuestionId(String questionId);

}
