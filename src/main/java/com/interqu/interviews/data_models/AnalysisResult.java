package com.interqu.interviews.data_models;

import org.springframework.data.mongodb.core.mapping.Field;

import com.fasterxml.jackson.annotation.JsonProperty;

public class AnalysisResult {

	@Field("overall")
	@JsonProperty("overall")
	private OverallResult overallResult;
	
	@Field("video")
	@JsonProperty("video")
	private VideoResult videoResult;
	
	@Field("audio")
	@JsonProperty("audio")
	private AudioResult audioResult;
	
	@Field("context")
	@JsonProperty("context")
	private ContextResult contextResult;
	
	public AnalysisResult() {
		
	}
	
	public AnalysisResult(OverallResult overallResult, VideoResult videoResult, AudioResult audioResult,
			ContextResult contextResult) {
		super();
		this.overallResult = overallResult;
		this.videoResult = videoResult;
		this.audioResult = audioResult;
		this.contextResult = contextResult;
	}

	public OverallResult getOverallResult() {
		return overallResult;
	}

	public void setOverallResult(OverallResult overallResult) {
		this.overallResult = overallResult;
	}

	public VideoResult getVideoResult() {
		return videoResult;
	}

	public void setVideoResult(VideoResult videoResult) {
		this.videoResult = videoResult;
	}

	public AudioResult getAudioResult() {
		return audioResult;
	}

	public void setAudioResult(AudioResult audioResult) {
		this.audioResult = audioResult;
	}

	public ContextResult getContextResult() {
		return contextResult;
	}

	public void setContextResult(ContextResult contextResult) {
		this.contextResult = contextResult;
	}
	
}
