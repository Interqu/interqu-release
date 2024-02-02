package com.interqu.interviews;

import java.util.List;

import javax.persistence.Id;

import org.springframework.data.mongodb.core.mapping.Document;

@Document("interview_results")
public class Result {
    
	@Id
	private String id; // Id of the interview
	
    private String fileId; // the file name id that can be found in the AWS S3 buckets
    private String email; // account
    
    private String questionId; // question the interview result relates to
    
    private String timestamp; // the time the user took the interview
    
    private int videoLength; //stored in seconds
    
    private double overallScore; // overall score of the user
    private String overallSummary; // overall performance summary (a summary of video, audio, and context score)
    
    private double videoScore; // overall video score (out of 100)
    private List<String> videoTimestamps; // each entry is one second and the average emotion within that second. 
    private String videoFeedBack; // paragraph summarizing the video part.

    private double audioScore; // overall audio score (out of 100)
    private List<String> audioTimestamps; // each entry is one second of audio and the emotion detected within that second/
    private String audioFeedback; // paragraph summarizing the audio.

    private double contextScore; // overall context score (out of 100)
    private String transcript; // transcript of the interview
    private String contextFeedback; // paragraph summarizing the content spoken.

    public Result(){
        
    }

	public Result(String id, String fileId, String email, String questionId, String timestamp, int videoLength,
			double overallScore, String overallSummary, double videoScore, List<String> videoTimestamps,
			String videoFeedBack, double audioScore, List<String> audioTimestamps, String audioFeedback,
			double contextScore, String transcript, String contextFeedback) {
		super();
		this.id = id;
		this.fileId = fileId;
		this.email = email;
		this.questionId = questionId;
		this.timestamp = timestamp;
		this.videoLength = videoLength;
		this.overallScore = overallScore;
		this.overallSummary = overallSummary;
		this.videoScore = videoScore;
		this.videoTimestamps = videoTimestamps;
		this.videoFeedBack = videoFeedBack;
		this.audioScore = audioScore;
		this.audioTimestamps = audioTimestamps;
		this.audioFeedback = audioFeedback;
		this.contextScore = contextScore;
		this.transcript = transcript;
		this.contextFeedback = contextFeedback;
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

	public double getOverallScore() {
		return overallScore;
	}

	public void setOverallScore(double overallScore) {
		this.overallScore = overallScore;
	}

	public String getOverallSummary() {
		return overallSummary;
	}

	public void setOverallSummary(String overallSummary) {
		this.overallSummary = overallSummary;
	}

	public double getVideoScore() {
		return videoScore;
	}

	public void setVideoScore(double videoScore) {
		this.videoScore = videoScore;
	}

	public List<String> getVideoTimestamps() {
		return videoTimestamps;
	}

	public void setVideoTimestamps(List<String> videoTimestamps) {
		this.videoTimestamps = videoTimestamps;
	}

	public String getVideoFeedBack() {
		return videoFeedBack;
	}

	public void setVideoFeedBack(String videoFeedBack) {
		this.videoFeedBack = videoFeedBack;
	}

	public double getAudioScore() {
		return audioScore;
	}

	public void setAudioScore(double audioScore) {
		this.audioScore = audioScore;
	}

	public List<String> getAudioTimestamps() {
		return audioTimestamps;
	}

	public void setAudioTimestamps(List<String> audioTimestamps) {
		this.audioTimestamps = audioTimestamps;
	}

	public String getAudioFeedback() {
		return audioFeedback;
	}

	public void setAudioFeedback(String audioFeedback) {
		this.audioFeedback = audioFeedback;
	}

	public double getContextScore() {
		return contextScore;
	}

	public void setContextScore(double contextScore) {
		this.contextScore = contextScore;
	}

	public String getTranscript() {
		return transcript;
	}

	public void setTranscript(String transcript) {
		this.transcript = transcript;
	}

	public String getContextFeedback() {
		return contextFeedback;
	}

	public void setContextFeedback(String contextFeedback) {
		this.contextFeedback = contextFeedback;
	}

	@Override
	public String toString() {
		return "Result [id=" + id + ", fileId=" + fileId + ", email=" + email + ", questionId=" + questionId
				+ ", timestamp=" + timestamp + ", videoLength=" + videoLength + ", overallScore=" + overallScore
				+ ", overallSummary=" + overallSummary + ", videoScore=" + videoScore + ", videoTimestamps="
				+ videoTimestamps + ", videoFeedBack=" + videoFeedBack + ", audioScore=" + audioScore
				+ ", audioTimestamps=" + audioTimestamps + ", audioFeedback=" + audioFeedback + ", contextScore="
				+ contextScore + ", transcript=" + transcript + ", contextFeedback=" + contextFeedback + "]";
	}

}
