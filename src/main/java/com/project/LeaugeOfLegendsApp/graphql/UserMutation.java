package com.project.LeaugeOfLegendsApp.graphql;

import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Component;

import com.project.LeaugeOfLegendsApp.model.User;
import com.project.LeaugeOfLegendsApp.service.AuthService;
import com.project.LeaugeOfLegendsApp.service.UserService;
import com.project.LeaugeOfLegendsApp.util.JwtResponse;
import com.project.LeaugeOfLegendsApp.util.LoginRequest;
import com.project.LeaugeOfLegendsApp.util.SignupRequest;

import graphql.kickstart.tools.GraphQLMutationResolver;
import lombok.RequiredArgsConstructor;

@Component
@RequiredArgsConstructor
public class UserMutation implements GraphQLMutationResolver {

	private final UserService userService;
	private final AuthService authService;
	
	public User createUser(User user) {
		return userService.createUser(user);
	}
	
	public String registerUser(SignupRequest signupRequest){
		return  authService.registerUser(signupRequest).getBody().toString();
	}
	
	@PreAuthorize("isAnonymous()")
	public JwtResponse authenticateUser(LoginRequest loginRequest) throws Exception{
		return (JwtResponse) authService.authenticateUser(loginRequest).getBody();
	
	}
}
