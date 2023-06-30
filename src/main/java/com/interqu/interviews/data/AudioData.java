package com.interqu.interviews.data;

import java.util.List;

import com.interqu.interviews.emotions.AudioEmotions;

public class AudioData {

    private double score;
    private List<AudioEmotions> emotions;
    //TODO implement tone - like confidence, studdering, etc.. 
    
    public AudioData(double score, List<AudioEmotions> emotions) {
        this.score = score;
        this.emotions = emotions;
    }

    public double getScore() {
        return score;
    }

    public void setScore(double score) {
        this.score = score;
    }

    public List<AudioEmotions> getEmotions() {
        return emotions;
    }

    public void setEmotions(List<AudioEmotions> emotions) {
        this.emotions = emotions;
    }
}
