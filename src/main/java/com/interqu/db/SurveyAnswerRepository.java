package com.interqu.db;

import org.springframework.data.mongodb.repository.MongoRepository;

import com.interqu.survey.SurveyAnswer;
import com.interqu.survey.SurveyType;

public interface SurveyAnswerRepository extends MongoRepository<SurveyAnswer, String>{

    public SurveyAnswer findBySurveyTypeAndEmail(SurveyType surveyType, String email);

}
