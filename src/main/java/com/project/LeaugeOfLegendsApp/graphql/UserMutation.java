package com.project.LeaugeOfLegendsApp.graphql;

import org.springframework.stereotype.Component;

import com.project.LeaugeOfLegendsApp.model.User;
import com.project.LeaugeOfLegendsApp.service.UserService;

import graphql.kickstart.tools.GraphQLMutationResolver;
import lombok.RequiredArgsConstructor;

@Component
@RequiredArgsConstructor
public class UserMutation implements GraphQLMutationResolver {

	private final UserService userService;
	
	public User createUser(User user) {
		return userService.createUser(user);
		
	}

}
