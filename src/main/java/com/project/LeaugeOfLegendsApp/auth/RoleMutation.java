package com.project.LeaugeOfLegendsApp.auth;

import graphql.kickstart.tools.GraphQLMutationResolver;
import lombok.RequiredArgsConstructor;
import org.springframework.security.access.annotation.Secured;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class RoleMutation implements GraphQLMutationResolver {

    private final RoleService roleService;

    @Secured({"ROLE_ADMIN", "ROLE_SUPERADMIN"})
    public Role createRole(Role role) {
        return roleService.createRole(role);
    }

}
