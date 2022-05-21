package com.project.LeaugeOfLegendsApp.graphql;

import org.springframework.stereotype.Component;

import com.coxautodev.graphql.tools.GraphQLMutationResolver;
import com.project.LeaugeOfLegendsApp.service.UserService;

import lombok.RequiredArgsConstructor;

@Component
@RequiredArgsConstructor
public class UserMutation implements GraphQLMutationResolver {

	private final UserService userService;

}
