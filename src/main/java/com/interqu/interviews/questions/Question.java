package com.interqu.interviews.questions;

import java.util.List;

import javax.persistence.Entity;
import javax.persistence.Id;

import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;

import com.fasterxml.jackson.annotation.JsonProperty;

@Entity
@Document("interview_questions")
public class Question {

	private String id;
	
    @Id
    @Field("question_id")
    @JsonProperty("question_id")
    private String questionId;
    
    @Field("position")
    @JsonProperty("position")
    private String position;
    
    @Field("quesiton")
    @JsonProperty("question")
    private String question;
    
    @Field("companies")
    @JsonProperty("companies")
    private List<String> companies;
    
    @Field("skills")
    @JsonProperty("skills")
    private List<String> skills;
    
    @Field("rating")
    @JsonProperty("rating")
    private int ratings;
    @Field("is_verified")
    @JsonProperty("is_verified")
    private boolean isVerified;
    
    @Field("tips")
    @JsonProperty("tips")
    private List<String> tips;
    
    @Field("employers_look_for")
    @JsonProperty("employers_look_for")
    private List<String> employersLookFor;
    
    @Field("avoid_mentioning")
    @JsonProperty("avoid_mentioning")
    private List<String> avoidMentioning;
    
    @Field("average_overall_rating")
    @JsonProperty("average_overall_rating")
    private double averageOverallRating;
    
    @Field("average_audio_rating")
    @JsonProperty("average_audio_rating")
    private double averageAudioRating;
    
    @Field("average_video_rating")
    @JsonProperty("average_video_rating")
    private double averageVideoRating;
    
    @Field("average_context_rating")
    @JsonProperty("average_context_rating")
    private double averageContextRating;
    
    public Question() {
    	
    }
    
	public Question(String questionId, String position, String question, List<String> companies, List<String> skills,
			int ratings, boolean isVerified, List<String> tips, List<String> employersLookFor,
			List<String> avoidMentioning, double averageOverallRating, double averageAudioRating,
			double averageVideoRating, double averageContextRating) {
		super();
		this.questionId = questionId;
		this.position = position;
		this.question = question;
		this.companies = companies;
		this.skills = skills;
		this.ratings = ratings;
		this.isVerified = isVerified;
		this.tips = tips;
		this.employersLookFor = employersLookFor;
		this.avoidMentioning = avoidMentioning;
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

	public List<String> getCompanies() {
		return companies;
	}

	public void setCompanies(List<String> companies) {
		this.companies = companies;
	}

	public List<String> getSkills() {
		return skills;
	}

	public void setSkills(List<String> skills) {
		this.skills = skills;
	}

	public int getRatings() {
		return ratings;
	}

	public void setRatings(int ratings) {
		this.ratings = ratings;
	}

	public boolean isVerified() {
		return isVerified;
	}

	public void setVerified(boolean isVerified) {
		this.isVerified = isVerified;
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

	public List<String> getAvoidMentioning() {
		return avoidMentioning;
	}

	public void setAvoidMentioning(List<String> avoidMentioning) {
		this.avoidMentioning = avoidMentioning;
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
		return "Question [questionId=" + questionId + ", position=" + position + ", question=" + question
				+ ", companies=" + companies + ", skills=" + skills + ", ratings=" + ratings + ", isVerified="
				+ isVerified + ", tips=" + tips + ", employersLookFor=" + employersLookFor + ", avoidMentioning="
				+ avoidMentioning + ", averageOverallRating=" + averageOverallRating + ", averageAudioRating="
				+ averageAudioRating + ", averageVideoRating=" + averageVideoRating + ", averageContextRating="
				+ averageContextRating + "]";
	}
	
}
