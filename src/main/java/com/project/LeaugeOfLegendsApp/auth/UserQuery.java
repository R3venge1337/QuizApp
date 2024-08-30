package com.project.LeaugeOfLegendsApp.auth;

import java.util.List;

import graphql.kickstart.annotations.GraphQLQueryResolver;
import org.springframework.stereotype.Component;

import lombok.RequiredArgsConstructor;

@Component
@RequiredArgsConstructor
@GraphQLQueryResolver
public class UserQuery {
	
	private final UserService userService;
	
	public List<User> getAllUsers(int limit) {
		return userService.getAllUsers(limit);
	}
	
	public User findByUsername(String username) {
		return userService.findByUsername(username);
	}

}
