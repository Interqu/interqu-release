package com.interqu.interviews.questions;

import java.util.List;
import java.util.Map;

import javax.persistence.Id;

import org.springframework.data.mongodb.core.mapping.Document;

@Document("interview_tips")
public class QuestionTips {

    @Id
    private String questionId;
    private String question;
    private List<String> tips;

    public QuestionTips(){

    }

    public QuestionTips(List<String>tips){
        this(null,tips);
    }

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
        return "QuestionTips [questionId=" + questionId + ", question=" + question + ", tips=" + tips + "]";
    }

    public String getQuestionId() {
        return questionId;
    }

    public void setQuestionId(String questionId) {
        this.questionId = questionId;
    }

}
