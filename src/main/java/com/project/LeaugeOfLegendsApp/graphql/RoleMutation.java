package com.project.LeaugeOfLegendsApp.graphql;

import org.springframework.stereotype.Component;

import com.project.LeaugeOfLegendsApp.model.Role;
import com.project.LeaugeOfLegendsApp.service.RoleService;

import graphql.kickstart.tools.GraphQLMutationResolver;
import lombok.RequiredArgsConstructor;

@Component
@RequiredArgsConstructor
public class RoleMutation implements GraphQLMutationResolver {
	
	private final RoleService roleService;
	
	public Role createRole(Role role) {
		return roleService.createRole(role);
	}

}
