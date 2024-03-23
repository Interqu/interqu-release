package com.interqu.roles;

import java.util.UUID;

import javax.persistence.Id;

public class Role {
	
	enum Roles{
		USER,
		ADMIN,
	}
	
    @Id
    private String id;
    private String name;

    public Role(){
        
    }

    public Role(String name) {
        this(UUID.randomUUID().toString(), name);
    }

    public Role(String id, String name) {
        this.id = id;
        this.name = name;
    }
    public String getId() {
        return id;
    }
    public void setId(String id) {
        this.id = id;
    }
    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }

}
