package com.interqu.db;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.aggregation.*;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.stereotype.Service;

import com.interqu.interviews.Result;
import java.util.List;

@Service
public class InterviewResultService {

    @Autowired
    private MongoTemplate mongoTemplate;

    public List<Result> findInterviewResultsByEmail(String email) {
        // Match users by email
        MatchOperation matchUserByEmail = Aggregation.match(Criteria.where("users_data.email").is(email));

        // Lookup (join) InterviewResults based on userId
        LookupOperation lookupInterviewResults = LookupOperation.newLookup()
                .from("users") // The collection to join
                .localField("user_id") // Field in the users collection
                .foreignField("_id") // Corresponding field in interviewResults collection
                .as("users_data");

        // Define the aggregation pipeline
        Aggregation aggregation = Aggregation.newAggregation(
                lookupInterviewResults,
                matchUserByEmail
        );

        // Execute the aggregation
        AggregationResults<Result> results = mongoTemplate.aggregate(aggregation, "interview_results", Result.class);

        return results.getMappedResults();
    }
}
