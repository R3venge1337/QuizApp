package com.project.LeaugeOfLegendsApp.auth;

import java.util.List;


import lombok.Data;

@Data
public class JwtResponse {
	
	private String token;
	private String type = "Bearer";
	private String id;
	private String username;
	private String email;
	private List<String> roles;
	
	public JwtResponse(final String accessToken, final String id, final String username, final String email, final List<String> roles) {
		this.token = accessToken;
		this.id = id;
		this.username = username;
		this.email = email;
		this.roles = roles;
	}
}
