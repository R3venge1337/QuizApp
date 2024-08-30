package com.project.LeaugeOfLegendsApp.auth;

import java.util.List;

import graphql.kickstart.annotations.GraphQLQueryResolver;
import org.springframework.stereotype.Component;

import lombok.RequiredArgsConstructor;

@Component
@RequiredArgsConstructor
@GraphQLQueryResolver
public class RoleQuery {

	private final RoleService roleService;

	public List<Role> getAllRoles() {
		return roleService.findAllRoles();
	}

	public Role getRoleByName(ERole roleName) {
		return roleService.findByName(roleName).orElseThrow();
	}
	
};