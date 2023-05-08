package com.interqu.db;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Service;

import com.interqu.user.PendingUser;

@Service
public interface PendingUserRepository extends MongoRepository<PendingUser, String> {

    PendingUser findByHash(String hash);

    PendingUser findByEmail(String email);

}
