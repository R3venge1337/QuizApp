package com.project.LeaugeOfLegendsApp.graphql;

import org.springframework.security.access.annotation.Secured;
import org.springframework.stereotype.Component;

import com.project.LeaugeOfLegendsApp.model.Role;
import com.project.LeaugeOfLegendsApp.service.RoleService;

import graphql.kickstart.tools.GraphQLMutationResolver;
import lombok.RequiredArgsConstructor;

@Component
@RequiredArgsConstructor
public class RoleMutation implements GraphQLMutationResolver {
	
	private final RoleService roleService;
	
	@Secured({"ROLE_ADMIN", "ROLE_SUPERADMIN"})
	public Role createRole(Role role) {
		return roleService.createRole(role);
	}

}
