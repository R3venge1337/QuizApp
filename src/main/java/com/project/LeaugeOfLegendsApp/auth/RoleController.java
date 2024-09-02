package com.project.LeaugeOfLegendsApp.auth;

import lombok.RequiredArgsConstructor;
import org.springframework.graphql.data.method.annotation.Argument;
import org.springframework.graphql.data.method.annotation.MutationMapping;
import org.springframework.graphql.data.method.annotation.QueryMapping;
import org.springframework.security.access.annotation.Secured;
import org.springframework.stereotype.Controller;

import java.util.List;

@Controller
@RequiredArgsConstructor
class RoleController {

    private final RoleService roleService;

    @QueryMapping
    List<Role> getAllRoles() {
        return roleService.findAllRoles();
    }

    @QueryMapping
    Role getRoleByName(@Argument final ERole roleName) {
        return roleService.findByName(roleName).orElseThrow();
    }

    @Secured({"ROLE_ADMIN", "ROLE_SUPERADMIN"})
    @MutationMapping
    Role createRole(@Argument final Role role) {
        return roleService.createRole(role);
    }
}
