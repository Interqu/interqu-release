package com.interqu.db;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Service;

import com.interqu.interviews.questions.QuestionTips;

@Service
public interface QuestionTipsRepository extends MongoRepository<QuestionTips, String> {

    QuestionTips findByQuestion(String question);

}
