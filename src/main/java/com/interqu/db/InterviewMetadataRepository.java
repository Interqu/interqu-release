package com.interqu.db;

import java.util.List;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Service;

import com.interqu.interviews.InterviewMetadata;

@Service
public interface InterviewMetadataRepository extends MongoRepository<InterviewMetadata, String> {
    
    public List<InterviewMetadata> findByUser(String user);

}
