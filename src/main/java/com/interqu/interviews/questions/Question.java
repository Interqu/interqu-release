package com.interqu.interviews.questions;

import java.util.ArrayList;
import java.util.List;

import org.springframework.data.mongodb.core.mapping.Document;

@Document("interview_questions_with_tips")
public class Question {

    private String position;
    private String question;
    // What interviewers want to see
    private List<String> positiveIndicators = new ArrayList<String>();
    // What qinterviewers dont want to see (Things to avoid in the interview)
    private List<String> negativeIndicators = new ArrayList<String>();
    private String tipsForAnswer;

    public Question(String position, String question, List<String> positiveIndicators,
            List<String> negativeIndicators, String tipsForAnswer) {
        this.position = position;
        this.question = question;
        this.positiveIndicators = positiveIndicators;
        this.negativeIndicators = negativeIndicators;
        this.tipsForAnswer = tipsForAnswer;
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

    public String getTipsForAnswer() {
        return tipsForAnswer;
    }

    public void setTipsForAnswer(String tipsForAnswer) {
        this.tipsForAnswer = tipsForAnswer;
    }

    @Override
    public String toString() {
        return "Question [position=" + position + ", question=" + question + ", positiveIndicators="
                + positiveIndicators + ", negativeIndicators=" + negativeIndicators + ", tipsForAnswer=" + tipsForAnswer
                + "]";
    }
}
