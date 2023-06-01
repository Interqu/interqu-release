package com.interqu.db;

import org.springframework.data.mongodb.repository.MongoRepository;

import com.interqu.survey.SurveyQuestions;
import com.interqu.survey.SurveyType;

public interface SurveyQuestionRepository extends MongoRepository<SurveyQuestions, String>{
    
    public SurveyQuestions findBySurveyType(SurveyType surveyType);

}
