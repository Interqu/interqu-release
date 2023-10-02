package com.interqu.process;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import com.interqu.interviews.questions.Question;
import com.interqu.user.User;

import software.amazon.awssdk.regions.Region;
import software.amazon.awssdk.services.s3.model.PutObjectRequest;
import software.amazon.awssdk.services.s3.presigner.model.PresignedPutObjectRequest;
import software.amazon.awssdk.services.s3.presigner.S3Presigner;
import software.amazon.awssdk.services.s3.presigner.model.PutObjectPresignRequest;

import java.time.Duration;
import java.util.HashMap;
import java.util.Map;


@Service
public class S3Service {
    

    private String bucketName;

    private final S3Presigner s3Presigner = S3Presigner.builder().region(Region.US_EAST_1).build();

    public S3Service(@Value("${aws.bucket.name}") String bucketName){
        this.bucketName = bucketName;
    }

    public String generatePresignedUrl(User user, Question question, Map<String, String> metadata, String videoTitle) {
        PutObjectRequest objectRequest = PutObjectRequest.builder()
            .bucket(bucketName)
            .key(videoTitle)
            .contentType("video/mp4")
            .metadata(metadata)
            .build();

        PutObjectPresignRequest presignRequest = PutObjectPresignRequest.builder()
            .signatureDuration(Duration.ofMinutes(10))
            .putObjectRequest(objectRequest)
            .build();

        PresignedPutObjectRequest presignedRequest = s3Presigner.presignPutObject(presignRequest);
        System.out.println("Presigned URL to upload a file to: " + presignedRequest.url());
        System.out.println("Which HTTP method needs to be used when uploading a file: " + presignedRequest.httpRequest().method());
        return presignedRequest.url().toString();
    }
}
