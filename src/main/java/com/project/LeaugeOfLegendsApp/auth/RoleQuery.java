package com.project.LeaugeOfLegendsApp.auth;

import java.util.List;

import graphql.kickstart.tools.GraphQLQueryResolver;
import org.springframework.stereotype.Component;

import lombok.RequiredArgsConstructor;

@Component
@RequiredArgsConstructor
public class RoleQuery implements GraphQLQueryResolver {

	private final RoleService roleService;

	public List<Role> getAllRoles() {
		return roleService.findAllRoles();
	}

	public Role getRoleByName(ERole roleName) {
		return roleService.findByName(roleName).orElseThrow();
	}
	
};