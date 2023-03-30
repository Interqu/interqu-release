package com.interqu.interviews;

import java.util.HashMap;

public class InterviewData {

	private HashMap<String, String> videoStats = new HashMap<String,String>();
	private String videoAnalysis;
	private HashMap<String, String> audioStats = new HashMap<String, String>();
	private String audioAnalysis;
	private String contentAnalysis;
	
	public InterviewData(HashMap<String, String> videoStats, String videoAnalysis, HashMap<String, String> audioStats,
			String audioAnalysis, String contentAnalysis) {
		super();
		this.videoStats = videoStats;
		this.videoAnalysis = videoAnalysis;
		this.audioStats = audioStats;
		this.audioAnalysis = audioAnalysis;
		this.contentAnalysis = contentAnalysis;
	}

	public HashMap<String, String> getVideoStats() {
		return videoStats;
	}

	public void setVideoStats(HashMap<String, String> videoStats) {
		this.videoStats = videoStats;
	}

	public String getVideoAnalysis() {
		return videoAnalysis;
	}

	public void setVideoAnalysis(String videoAnalysis) {
		this.videoAnalysis = videoAnalysis;
	}

	public HashMap<String, String> getAudioStats() {
		return audioStats;
	}

	public void setAudioStats(HashMap<String, String> audioStats) {
		this.audioStats = audioStats;
	}

	public String getAudioAnalysis() {
		return audioAnalysis;
	}

	public void setAudioAnalysis(String audioAnalysis) {
		this.audioAnalysis = audioAnalysis;
	}

	public String getContentAnalysis() {
		return contentAnalysis;
	}


	public void setContentAnalysis(String contentAnalysis) {
		this.contentAnalysis = contentAnalysis;
	}

	@Override
	public String toString() {
		return "InterviewData [videoStats=" + videoStats + ", videoAnalysis=" + videoAnalysis + ", audioStats="
				+ audioStats + ", audioAnalysis=" + audioAnalysis + ", contentAnalysis=" + contentAnalysis + "]";
	}	
}
