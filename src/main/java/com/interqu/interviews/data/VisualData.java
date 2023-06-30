package com.interqu.interviews.data;

import java.util.ArrayList;
import java.util.List;

import org.json.JSONArray;
import org.json.JSONObject;

import com.interqu.interviews.emotions.VisualEmotions;

public class VisualData {
    
    private double score;
    private List<VisualEmotions> emotions;
    //TODO implement background data???? hand gestures???


    public VisualData(double score, List<VisualEmotions> emotions) {
        this.score = score;
        this.emotions = emotions;
    }

    public static VisualData fromJson(JSONObject object) throws Exception{
        JSONObject innerObject = object.getJSONObject("output");
        double score = innerObject.getDouble("Score");
        JSONArray jsonArray = innerObject.getJSONArray("Timeline");
        List<VisualEmotions> emotions = new ArrayList<VisualEmotions>();
        for (int i = 0; i < jsonArray.length(); i++) {
            emotions.add(VisualEmotions.valueOf(jsonArray.getString(i).toUpperCase()));
        }
        return new VisualData(score, emotions);
    }

    public double getScore() {
        return score;
    }

    public void setScore(double score) {
        this.score = score;
    }

    public List<VisualEmotions> getEmotions() {
        return emotions;
    }

    public void setEmotions(List<VisualEmotions> emotions) {
        this.emotions = emotions;
    }
    
}
