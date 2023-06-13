package com.interqu.interviews;

import java.util.List;

import org.springframework.data.mongodb.core.mapping.Document;

@Document("interview-results")
public class Result {
    
    private String fileName;
    private String email;//account
    private String position;
    private String prompt;
    private String timeTaken;
    private String videoTime;
    private int overallScore;
    
    private String overallVideoEmotionTitle;
    private int videoScore;
    private List<String> videoEmotion;
    private String videoFeedBack;

    private String overallAudioEmotionTitle;
    private int audioScore;
    private List<String> audioEmotion;
    private String audioFeedback;

    private String overallContextTitle;
    private int contextScore;
    private String contextFeedback;

    public Result(){
        
    }

    public Result(String fileName, String email, String position, String prompt, String timeTaken, String videoTime,
            int overallScore, String overallVideoEmotionTitle, int videoScore, List<String> videoEmotion,
            String videoFeedBack, String overallAudioEmotionTitle, int audioScore, List<String> audioEmotion,
            String audioFeedback, String overallContextTitle, int contextScore, String contextFeedback) {
        this.fileName = fileName;
        this.email = email;
        this.position = position;
        this.prompt = prompt;
        this.timeTaken = timeTaken;
        this.videoTime = videoTime;
        this.overallScore = overallScore;
        this.overallVideoEmotionTitle = overallVideoEmotionTitle;
        this.videoScore = videoScore;
        this.videoEmotion = videoEmotion;
        this.videoFeedBack = videoFeedBack;
        this.overallAudioEmotionTitle = overallAudioEmotionTitle;
        this.audioScore = audioScore;
        this.audioEmotion = audioEmotion;
        this.audioFeedback = audioFeedback;
        this.overallContextTitle = overallContextTitle;
        this.contextScore = contextScore;
        this.contextFeedback = contextFeedback;
    }

    public String getFileName() {
        return fileName;
    }

    public void setFileName(String fileName) {
        this.fileName = fileName;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPosition() {
        return position;
    }

    public void setPosition(String position) {
        this.position = position;
    }

    public String getPrompt() {
        return prompt;
    }

    public void setPrompt(String prompt) {
        this.prompt = prompt;
    }

    public String getTimeTaken() {
        return timeTaken;
    }

    public void setTimeTaken(String timeTaken) {
        this.timeTaken = timeTaken;
    }

    public String getVideoTime() {
        return videoTime;
    }

    public void setVideoTime(String videoTime) {
        this.videoTime = videoTime;
    }

    public int getOverallScore() {
        return overallScore;
    }

    public void setOverallScore(int overallScore) {
        this.overallScore = overallScore;
    }

    public String getOverallVideoEmotionTitle() {
        return overallVideoEmotionTitle;
    }

    public void setOverallVideoEmotionTitle(String overallVideoEmotionTitle) {
        this.overallVideoEmotionTitle = overallVideoEmotionTitle;
    }

    public int getVideoScore() {
        return videoScore;
    }

    public void setVideoScore(int videoScore) {
        this.videoScore = videoScore;
    }

    public List<String> getVideoEmotion() {
        return videoEmotion;
    }

    public void setVideoEmotion(List<String> videoEmotion) {
        this.videoEmotion = videoEmotion;
    }

    public String getVideoFeedBack() {
        return videoFeedBack;
    }

    public void setVideoFeedBack(String videoFeedBack) {
        this.videoFeedBack = videoFeedBack;
    }

    public String getOverallAudioEmotionTitle() {
        return overallAudioEmotionTitle;
    }

    public void setOverallAudioEmotionTitle(String overallAudioEmotionTitle) {
        this.overallAudioEmotionTitle = overallAudioEmotionTitle;
    }

    public int getAudioScore() {
        return audioScore;
    }

    public void setAudioScore(int audioScore) {
        this.audioScore = audioScore;
    }

    public List<String> getAudioEmotion() {
        return audioEmotion;
    }

    public void setAudioEmotion(List<String> audioEmotion) {
        this.audioEmotion = audioEmotion;
    }

    public String getAudioFeedback() {
        return audioFeedback;
    }

    public void setAudioFeedback(String audioFeedback) {
        this.audioFeedback = audioFeedback;
    }

    public String getOverallContextTitle() {
        return overallContextTitle;
    }

    public void setOverallContextTitle(String overallContextTitle) {
        this.overallContextTitle = overallContextTitle;
    }

    public int getContextScore() {
        return contextScore;
    }

    public void setContextScore(int contextScore) {
        this.contextScore = contextScore;
    }

    public String getContextFeedback() {
        return contextFeedback;
    }

    public void setContextFeedback(String contextFeedback) {
        this.contextFeedback = contextFeedback;
    }

    
    

}
