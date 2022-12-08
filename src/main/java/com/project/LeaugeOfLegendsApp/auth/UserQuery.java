package com.project.LeaugeOfLegendsApp.auth;

import java.util.List;

import org.springframework.stereotype.Component;

import graphql.kickstart.tools.GraphQLQueryResolver;
import lombok.RequiredArgsConstructor;

@Component
@RequiredArgsConstructor
public class UserQuery implements GraphQLQueryResolver {
	
	private final UserService userService;
	
	public List<User> getAllUsers(int limit) {
		return userService.getAllUsers(limit);
	}
	
	public User findByUsername(String username) {
		return userService.findByUsername(username);
	}

}
