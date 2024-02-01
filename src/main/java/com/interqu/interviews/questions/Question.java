package com.interqu.interviews.questions;

import java.util.List;

import javax.persistence.Entity;
import javax.persistence.Id;

import org.springframework.data.mongodb.core.mapping.Document;

@Entity
@Document("interview_questions")
public class Question {

    @Id
    private String questionId;
    
    private String position;
    private String question;
    
    private List<String> tips;
    private List<String> employersLookFor;
    private List<String> avoidSaying;
    
    private double difficultyRating;
    
    private double averageOverallRating;
    private double averageAudioRating;
    private double averageVideoRating;
    private double averageContextRating;
    
    public Question() {
    	
    }
    
	public Question(String questionId, String position, String question, List<String> tips,
			List<String> employersLookFor, List<String> avoidSaying, double difficultyRating, double averageOverallRating,
			double averageAudioRating, double averageVideoRating, double averageContextRating) {
		super();
		this.questionId = questionId;
		this.position = position;
		this.question = question;
		this.tips = tips;
		this.employersLookFor = employersLookFor;
		this.avoidSaying = avoidSaying;
		this.difficultyRating = difficultyRating;
		this.averageOverallRating = averageOverallRating;
		this.averageAudioRating = averageAudioRating;
		this.averageVideoRating = averageVideoRating;
		this.averageContextRating = averageContextRating;
	}

	public String getQuestionId() {
		return questionId;
	}

	public void setQuestionId(String questionId) {
		this.questionId = questionId;
	}

	public String getPosition() {
		return position;
	}

	public void setPosition(String position) {
		this.position = position;
	}

	public String getQuestion() {
		return question;
	}

	public void setQuestion(String question) {
		this.question = question;
	}

	public List<String> getTips() {
		return tips;
	}

	public void setTips(List<String> tips) {
		this.tips = tips;
	}

	public List<String> getEmployersLookFor() {
		return employersLookFor;
	}

	public void setEmployersLookFor(List<String> employersLookFor) {
		this.employersLookFor = employersLookFor;
	}

	public List<String> getAvoidSaying() {
		return avoidSaying;
	}

	public void setAvoidSaying(List<String> avoidSaying) {
		this.avoidSaying = avoidSaying;
	}

	public double getDifficultyRating() {
		return difficultyRating;
	}

	public void setDifficultyRating(double difficultyRating) {
		this.difficultyRating = difficultyRating;
	}

	public double getAverageOverallRating() {
		return averageOverallRating;
	}

	public void setAverageOverallRating(double averageOverallRating) {
		this.averageOverallRating = averageOverallRating;
	}

	public double getAverageAudioRating() {
		return averageAudioRating;
	}

	public void setAverageAudioRating(double averageAudioRating) {
		this.averageAudioRating = averageAudioRating;
	}

	public double getAverageVideoRating() {
		return averageVideoRating;
	}

	public void setAverageVideoRating(double averageVideoRating) {
		this.averageVideoRating = averageVideoRating;
	}

	public double getAverageContextRating() {
		return averageContextRating;
	}

	public void setAverageContextRating(double averageContextRating) {
		this.averageContextRating = averageContextRating;
	}

	@Override
	public String toString() {
		return "Question [questionId=" + questionId + ", position=" + position + ", question=" + question + ", tips="
				+ tips + ", employersLookFor=" + employersLookFor + ", avoidSaying=" + avoidSaying
				+ ", difficultyRating=" + difficultyRating + ", averageOverallRating=" + averageOverallRating
				+ ", averageAudioRating=" + averageAudioRating + ", averageVideoRating=" + averageVideoRating
				+ ", averageContextRating=" + averageContextRating + "]";
	} 
	
}
