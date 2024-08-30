package com.project.LeaugeOfLegendsApp.auth;

import graphql.kickstart.annotations.GraphQLMutationResolver;
import org.springframework.security.access.annotation.Secured;
import org.springframework.stereotype.Component;
import lombok.RequiredArgsConstructor;

@Component
@RequiredArgsConstructor
@GraphQLMutationResolver
public class RoleMutation {
	
	private final RoleService roleService;
	
	@Secured({"ROLE_ADMIN", "ROLE_SUPERADMIN"})
	public Role createRole(Role role) {
		return roleService.createRole(role);
	}

}
