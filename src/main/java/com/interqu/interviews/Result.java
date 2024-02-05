package com.interqu.interviews;

import javax.persistence.Id;

import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;

import com.interqu.interviews.data_models.AnalysisResult;

@Document("interview_results")
public class Result {
    
	@Id
	private String id; // Id of the interview
	
	@Field("file_id")
    private String fileId; // the file name id that can be found in the AWS S3 buckets
	@Field("email")
    private String email; // account
    
	@Field("question_id")
    private String questionId; // question the interview result relates to
    
	@Field("timestamp")
    private String timestamp; // the time the user took the interview
    
	@Field("video_length")
    private int videoLength; //stored in seconds
    
	@Field("analysis")
    private AnalysisResult analysis; // analysis data of interview

	public Result() {
		
	}
	
	public Result(String id, String fileId, String email, String questionId, String timestamp, int videoLength,
			AnalysisResult analysis) {
		super();
		this.id = id;
		this.fileId = fileId;
		this.email = email;
		this.questionId = questionId;
		this.timestamp = timestamp;
		this.videoLength = videoLength;
		this.analysis = analysis;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getFileId() {
		return fileId;
	}

	public void setFileId(String fileId) {
		this.fileId = fileId;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getQuestionId() {
		return questionId;
	}

	public void setQuestionId(String questionId) {
		this.questionId = questionId;
	}

	public String getTimestamp() {
		return timestamp;
	}

	public void setTimestamp(String timestamp) {
		this.timestamp = timestamp;
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

}
