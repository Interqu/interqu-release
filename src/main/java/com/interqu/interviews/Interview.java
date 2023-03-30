package com.interqu.interviews;

import java.util.Date;

public class Interview {

	private String videoLink;
	private Date dateTaken;
	private InterviewData interviewData;
	
	public Interview(String videoLink, Date dateTaken, InterviewData interviewData) {
		super();
		this.videoLink = videoLink;
		this.dateTaken = dateTaken;
		this.interviewData = interviewData;
	}
	
	public String getVideoLink() {
		return videoLink;
	}
	public void setVideoLink(String videoLink) {
		this.videoLink = videoLink;
	}
	public Date getDateTaken() {
		return dateTaken;
	}
	public void setDateTaken(Date dateTaken) {
		this.dateTaken = dateTaken;
	}
	public InterviewData getInterviewData() {
		return interviewData;
	}
	public void setInterviewData(InterviewData interviewData) {
		this.interviewData = interviewData;
	}
	@Override
	public String toString() {
		return "Interview [videoLink=" + videoLink + ", dateTaken=" + dateTaken + ", interviewData=" + interviewData
				+ "]";
	}
	
}
