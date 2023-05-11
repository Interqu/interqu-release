package com.interqu.interviews.questions;

import java.util.ArrayList;
import java.util.List;

public class Question {
    
    private String question;
    // What interviewers want to see
    private List<String> positiveIndicators = new ArrayList<String>();
    // What qinterviewers dont want to see (Things to avoid in the interview)
    private List<String> negativeIndicators = new ArrayList<String>();

    public Question(String question, List<String> positiveIndicators, List<String> negativeIndicators) {
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

    public void addPositiveIndicators(String positiveIndicator){
        this.positiveIndicators.add(positiveIndicator);
    }

    public void addNegativeIndicators(String negativeIndicator){
        this.negativeIndicators.add(negativeIndicator);
    }

    @Override
    public String toString() {
        return "Questions [question=" + question + ", positiveIndicators=" + positiveIndicators
                + ", negativeIndicators=" + negativeIndicators + "]";
    }

}
