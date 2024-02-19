package com.interqu.utils;

import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.Date;
import java.util.UUID;

import javax.imageio.ImageIO;

import org.springframework.web.multipart.MultipartFile;

import com.interqu.interviews.InterviewVideoData;
import com.interqu.interviews.questions.Question;
import com.interqu.user.User;


public class Utils {
    
     public static InterviewVideoData setInterviewVideoData(User user, Question question){
        String id = UUID.randomUUID().toString();
        String fileName = user.getId() + "DAL" + question.getQuestionId() + "DAL" + id + ".mp4";
        return new InterviewVideoData(id, question.getQuestionId(), user.getId(), new Date(), fileName);
    }
     
     public static boolean assertImageDim(MultipartFile file, int width, int height) throws IOException {
         BufferedImage image = ImageIO.read(file.getInputStream());
         return image.getWidth() == width && image.getHeight() == height;
     }

}
