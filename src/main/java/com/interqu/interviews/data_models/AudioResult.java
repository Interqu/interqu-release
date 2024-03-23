package com.interqu.interviews.data_models;

import java.util.List;

import org.springframework.data.mongodb.core.mapping.Field;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.interqu.interviews.emotions.AudioEmotions;

public class AudioResult {

	@Field("audio_score")
	@JsonProperty("audio_score")
	private float score;
	
	@Field("audio_timestamps")
	@JsonProperty("audio_timestamps")
	private List<AudioEmotions> timestamps;
	
	@Field("audio_feedback")
	@JsonProperty("audio_feedback")
	private String feedback;
	
	public AudioResult() {
		
	}
	
	public AudioResult(float score, List<AudioEmotions> timestamps, String feedback) {
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
	public List<AudioEmotions> getTimestamps() {
		return timestamps;
	}
	public void setTimestamps(List<AudioEmotions> timestamps) {
		this.timestamps = timestamps;
	}
	public String getFeedback() {
		return feedback;
	}
	public void setFeedback(String feedback) {
		this.feedback = feedback;
	}
	
}
