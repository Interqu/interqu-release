package com.interqu.survey;

import java.util.HashMap;

import org.springframework.data.mongodb.core.mapping.Document;

@Document("survey_answers")
public class SurveyAnswer {
    
    private SurveyType surveyType;
    private String email;
    private HashMap<String, String> answers = new HashMap<>();
    
    public SurveyAnswer(){
        
    }

    public SurveyAnswer(SurveyType surveyType, String email, HashMap<String, String> answers) {
        this.surveyType = surveyType;
        this.email = email;
        this.answers = answers;
    }
    public SurveyType getSurveyType() {
        return surveyType;
    }
    public void setSurveyType(SurveyType surveyType) {
        this.surveyType = surveyType;
    }
    public String getEmail() {
        return email;
    }
    public void setEmail(String email) {
        this.email = email;
    }
    public HashMap<String, String> getAnswers() {
        return answers;
    }
    public void setAnswers(HashMap<String, String> answers) {
        this.answers = answers;
    }

    

}
