package com.project.LeaugeOfLegendsApp.auth;

import lombok.Data;

@Data
public class LoginRequest {
	String username;
	String password;
	
	public LoginRequest(final String username, final String password) {
		
		this.username = username;
		this.password = password;
	}
	
	
}
