package com.interqu.interviews.data_models;

import java.util.List;

import org.springframework.data.mongodb.core.mapping.Field;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.interqu.interviews.emotions.VisualEmotions;

public class VideoResult {

	@Field("video_score")
	@JsonProperty("video_score")
	private float score;
	
	@Field("video_timestamps")
	@JsonProperty("video_timestamps")
	private List<VisualEmotions> timestamps;
	
	@Field("video_feedback")
	@JsonProperty("video_feedback")
	private String feedback;
	
	public VideoResult() {
		
	}
	
	public VideoResult(float score, List<VisualEmotions> timestamps, String feedback) {
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
	public List<VisualEmotions> getTimestamps() {
		return timestamps;
	}
	public void setTimestamps(List<VisualEmotions> timestamps) {
		this.timestamps = timestamps;
	}
	public String getFeedback() {
		return feedback;
	}
	public void setFeedback(String feedback) {
		this.feedback = feedback;
	}
	
}
