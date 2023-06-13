package com.interqu.db;

import org.springframework.data.mongodb.repository.MongoRepository;

import com.interqu.roles.Role;

public interface RoleRepository extends MongoRepository<Role,String> {
    
    Role findByName(String name);

}
