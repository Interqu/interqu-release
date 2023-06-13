package com.interqu.utils;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Scanner;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;

import com.interqu.db.InterviewQuestionRepository;
import com.interqu.interviews.InterviewVideoData;
import com.interqu.interviews.questions.Question;
import com.interqu.user.User;

import software.amazon.awssdk.awscore.exception.AwsServiceException;
import software.amazon.awssdk.core.exception.SdkClientException;
import software.amazon.awssdk.core.sync.RequestBody;
import software.amazon.awssdk.services.s3.S3Client;
import software.amazon.awssdk.services.s3.model.PutObjectRequest;
import software.amazon.awssdk.services.s3.model.S3Exception;

public class Utils {

     private static final String BUCKET = "interqu-video";
    
     public static InterviewVideoData setInterviewVideoData(User user, Question question){
        String id = UUID.randomUUID().toString();
        String fileName = user.getId() + "DAL" + question.getQuestionId() + "DAL" + id + ".mp4";
        return new InterviewVideoData(id, question.getQuestionId(), user.getId(), new Date(), fileName);
    }

    public static void uploadFile(String fileName, InputStream inputStream)
            throws S3Exception, AwsServiceException, SdkClientException, IOException {
        S3Client client = S3Client.builder().build();
         
        PutObjectRequest request = PutObjectRequest.builder()
                            .bucket(BUCKET)
                            .key(fileName)
                            .build();
         
        client.putObject(request,
                RequestBody.fromInputStream(inputStream, inputStream.available()));
    }

    // @Autowired
    // private InterviewQuestionRepository iqRepo;

    // public static void extractQuestionFromCSV(String position, File file) {
    // Scanner scanner;
    // try {
    // scanner = new Scanner(file);

    // scanner.useDelimiter(",");
    // ArrayList<String> input = new ArrayList<String>();
    // while (scanner.hasNext()) {
    // input.add(scanner.next());
    // }
    // input.remove(0);
    // input.remove(0);
    // input.remove(0);
    // List<Question> questions = new ArrayList<Question>();
    // // Start at index 3; 10 for 10 questions
    // for (int i = 0; i < 10; i++) {
    // String title = input.get(0);
    // input.remove(0);
    // List<String> pos = new ArrayList<String>();
    // List<String> neg = new ArrayList<String>();
    // for (int j = 0; j < 10; j++) {
    // pos.add(input.get(0));
    // input.remove(0);
    // neg.add(input.get(0));
    // input.remove(0);
    // }
    // // questions.add(i, new Question(title, pos, neg));
    // iqRepo.insert(new Question(position, title, pos, neg));
    // }
    // scanner.close();
    // // return new InterviewQuestions("Consultant", questions);
    // } catch (FileNotFoundException e) {
    // // TODO Auto-generated catch block
    // e.printStackTrace();
    // }
    // }

}
