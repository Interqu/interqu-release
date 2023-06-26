package com.interqu.process;

import com.amazonaws.AmazonClientException;
import com.amazonaws.AmazonServiceException;
import com.amazonaws.services.s3.AmazonS3;
import com.amazonaws.services.s3.model.*;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

@Service
public class FileService {

   @Value("${aws.bucket.name}")
   private String bucketName;

   @Autowired
   private AmazonS3 s3Client;

    public String uploadFile(String keyName, MultipartFile file) {
        try {
            ObjectMetadata metadata = new ObjectMetadata();
            metadata.setContentLength(file.getSize());
            s3Client.putObject(bucketName, keyName, file.getInputStream(), metadata);
            return "File uploaded: " + keyName;
        } catch (IOException ioe) {
        } catch (AmazonServiceException serviceException) {
            throw serviceException;
        } catch (AmazonClientException clientException) {
        
            throw clientException;
        }
        return "File not uploaded: " + keyName;
    }

    public String getBucketName() {
        return bucketName;
    }

    public void setBucketName(String bucketName) {
        this.bucketName = bucketName;
    }

    public AmazonS3 getS3Client() {
        return s3Client;
    }

    public void setS3Client(AmazonS3 s3Client) {
        this.s3Client = s3Client;
    }
}