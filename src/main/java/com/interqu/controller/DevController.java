package com.interqu.controller;

import java.util.List;
import java.util.Random;
import java.util.UUID;

import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.interqu.interviews.Result;
import com.interqu.interviews.data.VisualData;
import com.interqu.interviews.questions.Question;

@Controller
@RequestMapping("dev")
public class DevController extends API{

    @GetMapping("/testAWSEmotionFunction")
    public ResponseEntity testAWSEmotion(@RequestParam("fileName") String fileName){
        try{
            VisualData vd = contentAnalyzer.generateInterviewVisualAnalysis(fileName);
            return new ResponseEntity<VisualData>(vd, HttpStatusCode.valueOf(200));
        }catch(Exception e){
            e.printStackTrace();
            return new ResponseEntity<>(e, HttpStatusCode.valueOf(500));
        }
    }
    
    //FOR DEV ONLY    
    @PostMapping("/insertQuestion")
    public ResponseEntity<String> insertQuestions(@RequestBody List<Question> interviewQuestions) throws Exception{
    	//process here
    	// Loop through all questions
    	for(Question question: interviewQuestions) {
          //ensure question is unique
          if(questionRepo.findByPositionAndQuestion(question.getPosition(), question.getQuestion())!=null){
              break;
          }
          
          // set position for now.
          if(question.getPosition() == null) {
              String position = "Software Engineer";
              question.setPosition(position);
          }

          // set id
          question.setQuestionId(UUID.randomUUID().toString());
          
          // Set RNG difficulting rating and average performance
          double difficultyMean = 40;
          double difficultySTD = 12;
          Random random = new Random();
          double difficultyRating =  (difficultyMean + difficultySTD * random.nextGaussian());
          difficultyRating =  (Math.round(difficultyRating * 100.0) / 100.0);
          
          double audioMean = 68;
          double videoMean = 53;
          double contentMean = 74;
          double meanSTD = 19;
          
          // calculating means
          double averageAudioRating =  (audioMean + meanSTD * random.nextGaussian()); 
          averageAudioRating =  (Math.round(averageAudioRating * 100.0) / 100.0);
          
          double averageVideoRating = (float) (videoMean + meanSTD * random.nextGaussian()); 
          averageVideoRating =  (Math.round(averageVideoRating * 100.0) / 100.0);
          
          double averageContextRating = (float) (contentMean + meanSTD * random.nextGaussian()); 
          averageContextRating =  (Math.round(averageContextRating * 100.0) / 100.0);
          
          double averageOverallRating = (averageAudioRating + averageVideoRating + averageContextRating)/3;
          averageOverallRating =  (Math.round(averageOverallRating * 100.0) / 100.0);
          
          // putting RND data into question obj.
          question.setDifficultyRating(difficultyRating);
          question.setAverageAudioRating(averageAudioRating);
          question.setAverageVideoRating(averageVideoRating);
          question.setAverageContextRating(averageContextRating);
          question.setAverageOverallRating(averageOverallRating);
          
          //FOR DEBUG
          logger.debug(question.toString());
          
          //save to db
          questionRepo.save(question);
    	}
    	return ResponseEntity.ok("Success.");
    }
    
    @PostMapping("/insertInterviewResult")
    public ResponseEntity<String> insertInterviewResult(@RequestBody List<Result> results) throws Exception{
    	
    	for(Result result : results) {
    		irRepo.save(result);
    	}
    	
    	return ResponseEntity.ok("Success.");
    }
    

}
