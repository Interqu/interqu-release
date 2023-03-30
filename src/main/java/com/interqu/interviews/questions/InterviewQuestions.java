package com.interqu.interviews.questions;

import java.util.ArrayList;
import java.util.List;

public class InterviewQuestions {

	private String jobPosition;
	private List<String> questions = new ArrayList<String>();
	
	public InterviewQuestions(String jobPosition, List<String> questions) {
		super();
		this.jobPosition = jobPosition;
		this.questions = questions;
	}
	
	public String getJobPosition() {
		return jobPosition;
	}
	public void setJobPosition(String jobPosition) {
		this.jobPosition = jobPosition;
	}
	public List<String> getQuestions() {
		return questions;
	}
	public void setQuestions(List<String> questions) {
		this.questions = questions;
	}
	
	
	
}
