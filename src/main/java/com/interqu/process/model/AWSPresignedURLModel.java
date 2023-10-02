package com.interqu.process.model;

import java.util.HashMap;
import java.util.Map;

public class AWSPresignedURLModel {
    
    private String presignedURL;
    private Map<String, String> metadata;
    private String fileName;

    public AWSPresignedURLModel(String presignedURL, Map<String, String> metadata, String fileName) {
        this.presignedURL = presignedURL;
        this.metadata = metadata;
        this.fileName = fileName;
    }
    public String getPresignedURL() {
        return presignedURL;
    }
    public void setPresignedURL(String presignedURL) {
        this.presignedURL = presignedURL;
    }
    public Map<String, String> getMetadata() {
        return metadata;
    }
    public void setMetadata(Map<String, String> metadata) {
        this.metadata = metadata;
    }
    public String getFileName() {
        return fileName;
    }
    public void setFileName(String fileName) {
        this.fileName = fileName;
    }

  

}
