package com.interqu.process;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.json.JSONArray;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpHeaders;

import com.interqu.interviews.Result;
import com.interqu.interviews.emotions.AudioEmotions;
import com.interqu.interviews.emotions.VisualEmotions;

public class ChatGPTService {
    
    @Autowired 
    private HttpConnectionService httpConnectionService;

    private static final String CHATGPT_API_URL = "";
    @Value("${CHATGPT_API_KEY}")
    private static final String API_KEY = "YOUR_API_KEY";

    private static final String GPT_MODEL = "gpt-3.5-turbo";

    //Visual
    private static final String VISUAL_FEEDBACK_SYSTEM_PROMPT = "You will act as a professional interview coach, speak in a professional manner, and will ONLY anaylze the facial emotions of an interviewee. In this query, you are provided an array of facial emotions detected by our system every second (i.e each element is the emotion detected within the second). Ensure you refer to the interviewee directly using pronouns like 'you', and ensure the lengths of your response is max of 400 words. Answer with the format: 'In this interview, you've....{what the array shows}...{what emotions should be displayed}...{helpful advice}...";
    private static final String VISUAL_FEEDBACK_USER_PROMPT = "This is an interview for a %s position. Question: %s. Array of emotions: %s";

    //Audio
    private static final String AUDIO_FEEDBACK_SYSTEM_PROMPT = "You will act as a professional interview coach, speak in a professional manner, and will ONLY anaylze the vocal emotions of an interviewee. In this query, you are provided an array of vocal emotions detected by our system every second (i.e each element is the emotion detected within the second). Ensure you refer to the interviewee directly using pronouns like 'you', and ensure the lengths of your response is max of 400 words. Answer with the format: 'In this interview, you've....{what the array shows}...{what emotions should be displayed}...{helpful advice}...";
    private static final String AUDIO_FEEDBACK_USER_PROMPT = "This is an interview for a %s position. Question: %s. Array of emotions: %s";

    //Content
    private static final String CONTENT_FEEDBACK_SYSTEM_PROMPT = "You will act as a professional interview coach, speak in a professional manner, and will ONLY anaylze the content of an interview response. In this query, you are provided with the transcript of an interview response, and you will give helpful advice of what was done well, or how to improve. Ensure you refer to the interviewee directly using pronouns like 'you', and ensure the lengths of your response is max of 400 words. Answer with the format: 'In this interview, you've....{what the array shows}...{what emotions should be displayed}...{helpful advice}...";
    private static final String CONTENT_FEEDBACK_USER_PROMPT = "This is an interview for a %s position. Question: %s. Interview Response: %s";

    // public Response Generate AllResponse
    public void generateFeedback(Result interviewResult) throws Exception{
        String position = interviewResult.getPosition();
        String question = interviewResult.getPrompt();
        
        //Visual
        interviewResult.setVideoFeedBack(generateVisualFeedback(position, question, interviewResult.getVideoEmotion()));
        //Audio
        interviewResult.setAudioFeedback(generateAudioFeedback(position, question, interviewResult.getAudioEmotion()));
        //Content
        interviewResult.setContextFeedback(generateContentFeedback(position, question, interviewResult.getTranscript()));
    }

    public String generateVisualFeedback(String position, String question, List<VisualEmotions> visualData) throws Exception{
        //Generate Visual Prompt
        String formatedUserPrompt = String.format(VISUAL_FEEDBACK_USER_PROMPT, position, question, visualData.toString());

        Map<String, Object> requestBody = generateResponseBody(VISUAL_FEEDBACK_SYSTEM_PROMPT, formatedUserPrompt);
        JSONObject response = httpConnectionService.executePOST(CHATGPT_API_URL, null, generateHeader(), requestBody);
        return parseResponse(response);
    } 

    public String generateAudioFeedback(String position, String question, List<AudioEmotions> audioData) throws Exception{
        //Generate Audio Prompt
        String formatedUserPrompt = String.format(AUDIO_FEEDBACK_USER_PROMPT, position, question, audioData.toString());

        Map<String, Object> requestBody = generateResponseBody(AUDIO_FEEDBACK_SYSTEM_PROMPT, formatedUserPrompt);
        JSONObject response = httpConnectionService.executePOST(CHATGPT_API_URL, null, generateHeader(), requestBody);
        return parseResponse(response);
    } 

    public String generateContentFeedback(String position, String question, String text) throws Exception{
        //Generate content Prompt
        String formatedUserPrompt = String.format(CONTENT_FEEDBACK_USER_PROMPT, position, question, text);

        Map<String, Object> requestBody = generateResponseBody(CONTENT_FEEDBACK_SYSTEM_PROMPT, formatedUserPrompt);
        JSONObject response = httpConnectionService.executePOST(CHATGPT_API_URL, null, generateHeader(), requestBody);
        return parseResponse(response);
    } 

    private Map<String, String> generateHeader(){
        Map<String, String> map = new HashMap<String, String>();

        map.put(HttpHeaders.CONTENT_TYPE, "application/json");
        map.put(HttpHeaders.AUTHORIZATION, "Bearer " + API_KEY);
        return map;
    }

    private String parseResponse(JSONObject response) throws Exception{
        //Get stop reason
        JSONArray choices = (JSONArray) response.get("choices");
        JSONObject choice = (JSONObject) choices.get(0);
        String stopReason = (String) choice.get("finish_reason");
        if(stopReason.equals("stop"))
            return (String) choice.get("message");
        throw new Exception("Unexpected error. Response Generation could not be complete. Finish Reason: " + stopReason);
    }

    private Map<String, Object> generateResponseBody(String systemPrompt, String userPrompt){
        Map<String, Object> requestBody = new HashMap<>();
        List<Map<String, String>> messages = new ArrayList<>();

        Map<String, String> systemMessageObj = new HashMap<>();
        systemMessageObj.put("role", "system");
        systemMessageObj.put("content", systemPrompt);
        messages.add(systemMessageObj);

        Map<String, String> userPromptObj = new HashMap<>();
        userPromptObj.put("role", "user");
        userPromptObj.put("content", userPrompt);
        messages.add(userPromptObj);

        requestBody.put("model", GPT_MODEL);
        requestBody.put("messages", messages);
        requestBody.put("max_tokens", 50);//configure max tokens

        return requestBody;
    }

    
}
