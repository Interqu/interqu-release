package com.interqu.S3;

import java.net.URL;
import java.util.Date;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.amazonaws.HttpMethod;
import com.amazonaws.services.s3.AmazonS3;
import com.amazonaws.services.s3.model.GeneratePresignedUrlRequest;

@Service
public class S3Service {
    
	@Autowired
	private AmazonS3 amazonS3;

    private static final String INTERQU_VIDEO_BUCKET = "interqu-video";
    private static final String AWS_METADATA_PREFIX = "x-amz-meta-";

    public URL generateInterviewPresignedUrl(String fileName, String userId, String questionId, String interviewId){
        Date expiration = new Date();
        long expTimeMillis = expiration.getTime();
        expTimeMillis += 1000 * 60 * 5; // 5 minutes
        expiration.setTime(expTimeMillis);

        GeneratePresignedUrlRequest generatePresignedUrlRequest =
                new GeneratePresignedUrlRequest(INTERQU_VIDEO_BUCKET, fileName)
                        .withMethod(HttpMethod.PUT)
                        .withExpiration(expiration);

        // Injecting metadata
        generatePresignedUrlRequest.addRequestParameter(AWS_METADATA_PREFIX + "user-id", userId);
        generatePresignedUrlRequest.addRequestParameter(AWS_METADATA_PREFIX + "question-id", questionId);
        generatePresignedUrlRequest.addRequestParameter(AWS_METADATA_PREFIX + "interview-id", interviewId);

        return amazonS3.generatePresignedUrl(generatePresignedUrlRequest);
    }

    public URL generateInterviewAccessUrl(String fileName){
        Date expiration = new Date();
        long expTimeMillis = expiration.getTime();
        expTimeMillis += 1000 * 60 * 60 * 5; // 5 hours
        expiration.setTime(expTimeMillis);

        GeneratePresignedUrlRequest generatePresignedUrlRequest = new GeneratePresignedUrlRequest(INTERQU_VIDEO_BUCKET, fileName)
                .withMethod(HttpMethod.GET)
                .withExpiration(expiration);

        return amazonS3.generatePresignedUrl(generatePresignedUrlRequest);
    }

}
