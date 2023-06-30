package com.interqu.process;

import java.net.URL;
import java.util.HashMap;

import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import com.interqu.interviews.data.VisualData;

@Component
public class ContentAnaylze {

    @Autowired
    private HttpConnectionService connectionService;

    //TODO put in config
    @Value("${analysis.visualUrl}")
    private String visualUrl;
    
    @Value("${analysis.apiKey}")
    private String apikey;

    public ContentAnaylze(){

    }

    //Includes, audio, visual, and content data
    // public InterviewData getInterviewAnalysis(String fileName){
    //     URL url = new URL(visualUrl);
    //     //TODO use only one link
    // }

    public VisualData generateInterviewVisualAnalysis(String fileName) throws Exception{
        HashMap<String, String> params = new HashMap<String, String>();
        params.put("file_name", fileName);
        HashMap<String, String> headers = new HashMap<String, String>();
        headers.put("X-API-KEY", apikey);
        headers.put("Content-Type", "application/json");
        JSONObject response = connectionService.executeGET(visualUrl, params,headers);
        System.out.println(response);
        return VisualData.fromJson(response);
    }

    // public AudioData generateInterviewAudioAnalysis(String fileName){

    // }

    // public String generateInterviewContentAnalysis(String fileName){

    // }

    // public String generateInterviewVisualDescription(VisualData visualData){

    // }

    // public String generateInterviewAudioDescription(AudioData audioData){
        
    // }

}
