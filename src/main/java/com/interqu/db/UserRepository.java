package com.interqu.db;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Service;

import com.interqu.user.User;

@Service
public interface UserRepository extends MongoRepository<User,String>{
	
	public User findByEmail(String email);
	
}
