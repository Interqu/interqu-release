package com.interqu.db;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Service;

import com.interqu.interviews.Position;

@Service
public interface PositionRepository extends MongoRepository<Position, String> {

}
