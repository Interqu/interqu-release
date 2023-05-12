package com.interqu.interviews.questions;

import org.springframework.data.mongodb.core.mapping.Document;

@Document("interview_tips")
public class QuestionTips {

    private String question;
    private String tips;

    public QuestionTips(String question, String tips) {
        this.question = question;
        this.tips = tips;
    }

    public String getQuestion() {
        return question;
    }

    public void setQuestion(String question) {
        this.question = question;
    }

    public String getTips() {
        return tips;
    }

    public void setTips(String tips) {
        this.tips = tips;
    }

    @Override
    public String toString() {
        return "QuestionTips [question=" + question + ", tips=" + tips + "]";
    }

}
