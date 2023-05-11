package com.interqu.interviews.questions;

import java.util.ArrayList;
import java.util.List;

import org.springframework.data.mongodb.core.mapping.Document;

@Document("interview_questions")
public class InterviewQuestions {

	private String jobPosition;
	private List<Question> questions = new ArrayList<Question>();
	
	public InterviewQuestions(String jobPosition, List<Question> questions) {
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
	public List<Question> getQuestions() {
		return questions;
	}
	public void setQuestions(List<Question> questions) {
		this.questions = questions;
	}
	public void addQuestions(Question question){
		this.questions.add(question);
	}
}
