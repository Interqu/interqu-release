package com.interqu.db;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Update;
import org.springframework.stereotype.Service;
import org.springframework.data.mongodb.core.query.Query;


import com.interqu.user.User;

@Service
public class UserRepositoryService {

    @Autowired
    private MongoTemplate mongoTemplate;

    public void updateProfilePicture(String email, byte[] newProfilePicture) {
        Query query = new Query(Criteria.where("email").is(email));
        Update update = new Update().set("profile_picture", newProfilePicture);
        mongoTemplate.updateFirst(query, update, User.class);
    }
	
}
