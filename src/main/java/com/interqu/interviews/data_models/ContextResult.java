package com.interqu.interviews.data_models;

import org.springframework.data.mongodb.core.mapping.Field;

public class ContextResult {

	@Field("context_score")
	private float score;
	@Field("transcript")
	private String transcript;
	@Field("context_feedback")
	private String feedback;
	
	public ContextResult() {
		
	}
	
	public ContextResult(float score, String transcript, String feedback) {
		super();
		this.score = score;
		this.transcript = transcript;
		this.feedback = feedback;
	}
	public float getScore() {
		return score;
	}
	public void setScore(float score) {
		this.score = score;
	}
	public String getTranscript() {
		return transcript;
	}
	public void setTranscript(String transcript) {
		this.transcript = transcript;
	}
	public String getFeedback() {
		return feedback;
	}
	public void setFeedback(String feedback) {
		this.feedback = feedback;
	}
	
}
