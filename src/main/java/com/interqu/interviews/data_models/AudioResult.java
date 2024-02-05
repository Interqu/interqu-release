package com.interqu.interviews.data_models;

import java.util.List;

import org.springframework.data.mongodb.core.mapping.Field;

public class AudioResult {

	@Field("audio_score")
	private float score;
	@Field("audio_timestamps")
	private List<String> timestamps;
	@Field("audio_feedback")
	private String feedback;
	
	public AudioResult() {
		
	}
	
	public AudioResult(float score, List<String> timestamps, String feedback) {
		super();
		this.score = score;
		this.timestamps = timestamps;
		this.feedback = feedback;
	}
	public float getScore() {
		return score;
	}
	public void setScore(float score) {
		this.score = score;
	}
	public List<String> getTimestamps() {
		return timestamps;
	}
	public void setTimestamps(List<String> timestamps) {
		this.timestamps = timestamps;
	}
	public String getFeedback() {
		return feedback;
	}
	public void setFeedback(String feedback) {
		this.feedback = feedback;
	}
	
}
