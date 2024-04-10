package com.interqu.S3;

import java.net.URL;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

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
        generatePresignedUrlRequest.addRequestParameter(AWS_METADATA_PREFIX + "user_id", userId);
        generatePresignedUrlRequest.addRequestParameter(AWS_METADATA_PREFIX + "file_id", fileName);
        generatePresignedUrlRequest.addRequestParameter(AWS_METADATA_PREFIX + "question_id", questionId);
        generatePresignedUrlRequest.addRequestParameter(AWS_METADATA_PREFIX + "interview_id", interviewId);

        return amazonS3.generatePresignedUrl(generatePresignedUrlRequest);
    }

}
