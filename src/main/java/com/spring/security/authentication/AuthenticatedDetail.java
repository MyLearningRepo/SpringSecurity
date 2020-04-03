package com.spring.security.authentication;

public class AuthenticatedDetail{
	
	private String id = null;
	private String name = null;
	
	public AuthenticatedDetail(String id) {
		this.id = id;
	}

	public String getId() {
		return id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
	
}
