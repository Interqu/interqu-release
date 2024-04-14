package com.interqu.interviews;

import java.util.Date;

import javax.persistence.Id;

import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.interqu.interviews.data_models.AnalysisResult;
import com.interqu.interviews.questions.Question;

@Document("interview_results")
public class Result {
    
	@Id
	@Field("interview_id")
	@JsonProperty("interview_id")
	private String interviewId; // Id of the interview
	
	@Field("file_id")
	@JsonProperty("file_id")
    private String fileId; // the file name id that can be found in the AWS S3 buckets
	
	@Field("user_id")
	@JsonProperty("user_id")
    private String userId; // account
    
	@Field("question_id")
	@JsonProperty("question_id")
    private String questionId; // question the interview result relates to
	
	@Field("question")
	@JsonProperty("question")
	private Question question;

	@Field("status")
	@JsonProperty("status")
	private Status status;
    
	@Field("timestamp")
	@JsonProperty("timestamp")
    private Date timestamp; // the time the user took the interview
    
	@Field("video_length")
	@JsonProperty("video_length")
    private int videoLength; //stored in seconds
    
	@Field("analysis")
	@JsonProperty("analysis")
    private AnalysisResult analysis; // analysis data of interview

	public Result() {
		
	}

	public String getInterviewId() {
		return interviewId;
	}

	public void setInterviewId(String id) {
		this.interviewId = id;
	}

	public String getFileId() {
		return fileId;
	}

	public void setFileId(String fileId) {
		this.fileId = fileId;
	}

	public String getUserId() {
		return userId;
	}

	public void setUserId(String email) {
		this.userId = email;
	}

	public String getQuestionId() {
		return questionId;
	}

	public void setQuestionId(String questionId) {
		this.questionId = questionId;
	}

	public int getVideoLength() {
		return videoLength;
	}

	public void setVideoLength(int videoLength) {
		this.videoLength = videoLength;
	}

	public AnalysisResult getAnalysis() {
		return analysis;
	}

	public void setAnalysis(AnalysisResult analysis) {
		this.analysis = analysis;
	}

	public Question getQuestion() {
		return question;
	}

	public void setQuestion(Question question) {
		this.question = question;
	}

	public Status getStatus() {
		return status;
	}

	public void setStatus(Status status) {
		this.status = status;
	}

	public Date getTimestamp() {
		return timestamp;
	}

	public void setTimestamp(Date timestamp) {
		this.timestamp = timestamp;
	}

	@Override
	public String toString() {
		return "Result [interview_id=" + interviewId + ", fileId=" + fileId + ", userId=" + userId + ", questionId=" + questionId
				+ ", timestamp=" + timestamp + ", videoLength=" + videoLength + ", analysis=" + analysis + "]";
	}
}
