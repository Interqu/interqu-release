package com.interqu.models;

import java.net.URL;

import com.fasterxml.jackson.annotation.JsonProperty;

public class S3PresignedIObject {
    
    @JsonProperty("presigned_url")
    private URL presignedUrl;
    @JsonProperty("video_file_name")
    private String videoName;
    @JsonProperty("interview_id")
    private String videoId;

    public S3PresignedIObject(URL presignedUrl, String videoName, String videoId) {
        this.presignedUrl = presignedUrl;
        this.videoName = videoName;
        this.videoId = videoId;
    }
    public URL getPresignedUrl() {
        return presignedUrl;
    }
    public void setPresignedUrl(URL presignedUrl) {
        this.presignedUrl = presignedUrl;
    }
    public String getVideoName() {
        return videoName;
    }
    public void setVideoName(String videoName) {
        this.videoName = videoName;
    }
    public String getVideoId() {
        return videoId;
    }
    public void setVideoId(String videoId) {
        this.videoId = videoId;
    }

    

}
