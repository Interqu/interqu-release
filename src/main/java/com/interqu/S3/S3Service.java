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

    public URL generateInterviewPresignedUrl(String fileName){
        Date expiration = new Date();
        long expTimeMillis = expiration.getTime();
        expTimeMillis += 1000 * 60 * 5; // 5 minutes
        expiration.setTime(expTimeMillis);

        GeneratePresignedUrlRequest generatePresignedUrlRequest =
                new GeneratePresignedUrlRequest(INTERQU_VIDEO_BUCKET, fileName)
                        .withMethod(HttpMethod.PUT)
                        .withExpiration(expiration);

        return amazonS3.generatePresignedUrl(generatePresignedUrlRequest);
    }

}
