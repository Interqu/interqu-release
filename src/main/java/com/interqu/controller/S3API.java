package com.interqu.controller;

import java.net.URL;
import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.amazonaws.HttpMethod;
import com.amazonaws.services.s3.AmazonS3;
import com.amazonaws.services.s3.model.GeneratePresignedUrlRequest;

@RestController
@RequestMapping("/api/s3")
public class S3API extends API{

	@Autowired
	private AmazonS3 amazonS3;
	
	//TODO places the bucket names in env file.
	private String getBucket(String fileName) {
		if(fileName.contains("resume")) {
			return "interqu-resumes";
		}else if(fileName.contains("video")) {
			return "interqu-video";
		}
		return "interqu";
	}
	
    @GetMapping("/generate-presigned-url")
    public URL generatePresignedUrl(@RequestParam("fileName") String fileName) {
    	logger.info("/generate-presigned-url: " + " generating presigned url. filename: " + fileName);
        Date expiration = new Date();
        long expTimeMillis = expiration.getTime();
        expTimeMillis += 1000 * 60 * 5; // 5 minutes
        expiration.setTime(expTimeMillis);

        GeneratePresignedUrlRequest generatePresignedUrlRequest =
                new GeneratePresignedUrlRequest(getBucket(fileName), fileName)
                        .withMethod(HttpMethod.PUT)
                        .withExpiration(expiration);

        return amazonS3.generatePresignedUrl(generatePresignedUrlRequest);
    }
	
}
