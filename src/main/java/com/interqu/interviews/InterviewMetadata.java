package com.interqu.interviews;

import java.util.Date;

import org.springframework.data.mongodb.core.mapping.Document;

@Document("interview_metadata")
public class InterviewMetadata {
    
    private String user;
    private String fileName;
    private Date timeTaken;
    private String score;
    private String processingStatus;
    private String questionId;

    
    public InterviewMetadata(String user, String fileName, Date timeTaken, String score, String processingStatus,
            String questionId) {
        this.user = user;
        this.fileName = fileName;
        this.timeTaken = timeTaken;
        this.score = score;
        this.processingStatus = processingStatus;
        this.questionId = questionId;
    }
    public String getUser() {
        return user;
    }
    public void setUser(String user) {
        this.user = user;
    }
    public Date getTimeTaken() {
        return timeTaken;
    }
    public void setTimeTaken(Date timeTaken) {
        this.timeTaken = timeTaken;
    }
    public String getScore() {
        return score;
    }
    public void setScore(String score) {
        this.score = score;
    }
    public String getProcessingStatus() {
        return processingStatus;
    }
    public void setProcessingStatus(String processingStatus) {
        this.processingStatus = processingStatus;
    }
    public String getQuestionId() {
        return questionId;
    }
    public void setQuestionId(String questionId) {
        this.questionId = questionId;
    }
    public String getFileName() {
        return fileName;
    }
    public void setFileName(String fileName) {
        this.fileName = fileName;
    }
    
    

}
