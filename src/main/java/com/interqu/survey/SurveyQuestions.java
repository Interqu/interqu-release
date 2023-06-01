package com.interqu.survey;

import org.springframework.data.mongodb.core.mapping.Document;

@Document("survey_questions")
public class SurveyQuestions {

    private SurveyType surveyType;
    private String[] questions;

    public SurveyQuestions(SurveyType surveyType, String[] questions){
        this.questions = questions;
        this.surveyType = surveyType;
    }

    public String[] getQuestions() {
        return questions;
    }

    public void setQuestions(String[] questions) {
        this.questions = questions;
    }

    public SurveyType getSurveyType() {
        return surveyType;
    }

    public void setSurveyType(SurveyType surveyType) {
        this.surveyType = surveyType;
    }
    
}
