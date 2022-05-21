package com.project.LeaugeOfLegendsApp.graphql;

import org.springframework.stereotype.Component;

import com.coxautodev.graphql.tools.GraphQLQueryResolver;
import com.project.LeaugeOfLegendsApp.service.UserService;

import lombok.RequiredArgsConstructor;

@Component
@RequiredArgsConstructor
public class UserQuery implements GraphQLQueryResolver {
	
	private final UserService userService;
}
