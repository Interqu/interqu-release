package com.interqu.interviews;

import java.util.Date;

import javax.persistence.Id;

import org.springframework.data.mongodb.core.mapping.Document;

@Document("interview_video_data")
public class InterviewVideoData {
    
    @Id
    private String id;
    private String questionId;
    
    private String userId;
    private Date dateTaken;

    private String fileName;

    public InterviewVideoData(String id, String questionId, String userId, Date dateTaken, String fileName) {
        this.id = id;
        this.questionId = questionId;
        this.userId = userId;
        this.dateTaken = dateTaken;
        this.fileName = fileName;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getQuestionId() {
        return questionId;
    }

    public void setQuestionId(String questionId) {
        this.questionId = questionId;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public Date getDateTaken() {
        return dateTaken;
    }

    public void setDateTaken(Date dateTaken) {
        this.dateTaken = dateTaken;
    }

    public String getFileName() {
        return fileName;
    }

    public void setFileName(String fileName) {
        this.fileName = fileName;
    }

    

}
