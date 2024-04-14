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

        private static final String ID = "interview_id";
        private static final String USER_COL_USER_ID = "_id";
        private static final String EMAIL = "email";
        private static final String USERS = "users";
        private static final String USER_ID = "user_id";
        private static final String USER_DATA = "user_data";
        //Collections TODO get from collections not defined here.
        private static final String INTERVIEW_QUESTIONS = "interview_questions";
        private static final String INTERVIEW_RESULTS = "interview_results";
        private static final String QUESTION_ID = "question_id";
        private static final String QUESTION = "question";


    @Autowired
    private MongoTemplate mongoTemplate;

    public Result findInterviewResultsById(String id, String email) {
        // Match interview by id
        MatchOperation matchInterviewById = Aggregation.match(Criteria.where(ID).is(id));
        
        // Match users by email
        MatchOperation matchUserByEmail = Aggregation.match(Criteria.where(USER_DATA + "." + EMAIL).is(email));

        // Lookup (join) InterviewResults based on userId
        LookupOperation lookupInterviewResults = LookupOperation.newLookup()
                .from(USERS) // The collection to join
                .localField(USER_ID) // Field in the users collection
                .foreignField(USER_COL_USER_ID) // Corresponding field in interviewResults collection
                .as(USER_DATA);

        LookupOperation lookupQuestionId = LookupOperation.newLookup()
                .from(INTERVIEW_QUESTIONS) // The collection to join
                .localField(QUESTION_ID) // Field in the users collection
                .foreignField(QUESTION_ID) // Corresponding field in interviewResults collection
                .as(QUESTION);
        

        // Join fields such as question or position
        // AddFieldsOperation operation = Aggregation.addFields()
        // .addFieldWithValue("question", "$question.question").addFieldWithValue("position", "$question.position").build();


        // Define the aggregation pipeline
        Aggregation aggregation = Aggregation.newAggregation(
                matchInterviewById,
                lookupInterviewResults,
                Aggregation.unwind(USER_DATA),
                matchUserByEmail,
                lookupQuestionId,
                Aggregation.unwind(QUESTION)
                // operation
        );

        // Execute the aggregation
        AggregationResults<Result> results = mongoTemplate.aggregate(aggregation, INTERVIEW_RESULTS, Result.class);

        if(results.getMappedResults().isEmpty()){
                return null;
        }

        return results.getMappedResults().get(0);
    }
    
    public List<Result> findInterviewResultsByEmail(String email) {
        // Match users by email
        MatchOperation matchUserByEmail = Aggregation.match(Criteria.where(USER_DATA + "." + EMAIL).is(email));

        // Lookup (join) InterviewResults based on userId
        LookupOperation lookupInterviewResults = LookupOperation.newLookup()
                .from(USERS) // The collection to join
                .localField(USER_ID) // Field in the users collection
                .foreignField(USER_COL_USER_ID) // Corresponding field in interviewResults collection
                .as(USER_DATA);

        LookupOperation lookupQuestionId = LookupOperation.newLookup()
                .from(INTERVIEW_QUESTIONS) // The collection to join
                .localField(QUESTION_ID) // Field in the users collection
                .foreignField(QUESTION_ID) // Corresponding field in interviewResults collection
                .as(QUESTION);
        

        // AddFieldsOperation operation = Aggregation.addFields()
        // .addFieldWithValue("question", "$question.question").addFieldWithValue("position", "$question.position").build();


        // Define the aggregation pipeline
        Aggregation aggregation = Aggregation.newAggregation(
                lookupInterviewResults,
                matchUserByEmail,
                Aggregation.unwind(USER_DATA),
                lookupQuestionId,
                Aggregation.unwind(QUESTION)
                // operation
        );

        // Execute the aggregation
        AggregationResults<Result> results = mongoTemplate.aggregate(aggregation, INTERVIEW_RESULTS, Result.class);

        return results.getMappedResults();
    }
}
