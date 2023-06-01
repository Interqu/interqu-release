package com.interqu.db;

import org.springframework.data.mongodb.repository.MongoRepository;

import com.interqu.user.BetaUser;

public interface BetaUserRepository extends MongoRepository<BetaUser, String> {

	BetaUser findByEmail(String email);
	
}
