package com.interqu.db;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Service;

import com.interqu.errors.ErrorReport;

@Service
public interface ErrorRepository extends MongoRepository<ErrorReport, String> {

	
	
}
