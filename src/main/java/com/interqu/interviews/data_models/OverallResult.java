package com.interqu.interviews.data_models;

import org.springframework.data.mongodb.core.mapping.Field;

public class OverallResult {

	@Field("overall_score")
	private float score;
	@Field("overall_summary")
	private String summary;
	
	public OverallResult(float score, String summary) {
		super();
		this.score = score;
		this.summary = summary;
	}
	
	public float getScore() {
		return score;
	}
	public void setScore(float score) {
		this.score = score;
	}
	public String getSummary() {
		return summary;
	}
	public void setSummary(String summary) {
		this.summary = summary;
	}
}
