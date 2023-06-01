package com.interqu.interviews.questions;

import java.util.List;

import org.springframework.data.mongodb.core.mapping.Document;

@Document("interview_tips")
public class QuestionTips {

    private String question;
    private List<String> tips;

    public QuestionTips(String question, List<String> tips) {
        this.question = question;
        this.tips = tips;
    }

    public String getQuestion() {
        return question;
    }

    public void setQuestion(String question) {
        this.question = question;
    }

    public List<String> getTips() {
        return tips;
    }

    public void setTips(List<String> tips) {
        this.tips = tips;
    }

    @Override
    public String toString() {
        return "QuestionTips [question=" + question + ", tips=" + tips + "]";
    }

}
