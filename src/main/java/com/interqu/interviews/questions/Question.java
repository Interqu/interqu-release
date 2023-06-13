package com.interqu.interviews.questions;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import org.springframework.data.mongodb.core.mapping.Document;

@Entity
@Document("interview_questions")
public class Question {

    @Id
    private String questionId;
    private String position;
    private String question;
    // What interviewers want to see
    private List<String> positiveIndicators = new ArrayList<String>();
    // What qinterviewers dont want to see (Things to avoid in the interview)
    private List<String> negativeIndicators = new ArrayList<String>();

    public Question(){

    }

    public Question(Map<String, Object> info){
        this((String)info.get("position"),(String) info.get("question"),(List<String>)info.get("positiveIndicators"),(List<String>)info.get("negativeIndicators"));
    }

    public Question(String position, String question, List<String> positiveIndicators,
            List<String> negativeIndicators) {
        questionId = UUID.randomUUID().toString();
        this.position = position;
        this.question = question;
        this.positiveIndicators = positiveIndicators;
        this.negativeIndicators = negativeIndicators;
    }

    public String getQuestion() {
        return question;
    }

    public void setQuestion(String question) {
        this.question = question;
    }

    public List<String> getPositiveIndicators() {
        return positiveIndicators;
    }

    public void setPositiveIndicators(List<String> positiveIndicators) {
        this.positiveIndicators = positiveIndicators;
    }

    public List<String> getNegativeIndicators() {
        return negativeIndicators;
    }

    public void setNegativeIndicators(List<String> negativeIndicators) {
        this.negativeIndicators = negativeIndicators;
    }

    public void addPositiveIndicators(String positiveIndicator) {
        this.positiveIndicators.add(positiveIndicator);
    }

    public void addNegativeIndicators(String negativeIndicator) {
        this.negativeIndicators.add(negativeIndicator);
    }

    public String getPosition() {
        return position;
    }

    public void setPosition(String position) {
        this.position = position;
    }

    

    @Override
    public String toString() {
        return "Question [questionId=" + questionId + ", position=" + position + ", question=" + question
                + ", positiveIndicators=" + positiveIndicators + ", negativeIndicators=" + negativeIndicators + "]";
    }

    public String getQuestionId() {
        return questionId;
    }
}
