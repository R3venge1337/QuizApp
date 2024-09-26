package com.project.LeaugeOfLegendsApp.auth.controller;

import com.project.LeaugeOfLegendsApp.auth.RoleFacade;
import com.project.LeaugeOfLegendsApp.auth.dto.RoleResponse;
import com.project.LeaugeOfLegendsApp.auth.dto.CreateRoleForm;
import com.project.LeaugeOfLegendsApp.auth.dto.FilterRoleForm;
import com.project.LeaugeOfLegendsApp.auth.dto.UpdateRoleForm;
import com.project.LeaugeOfLegendsApp.shared.controller.PageDto;
import com.project.LeaugeOfLegendsApp.shared.controller.PageableRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.graphql.data.method.annotation.Argument;
import org.springframework.graphql.data.method.annotation.MutationMapping;
import org.springframework.graphql.data.method.annotation.QueryMapping;
import org.springframework.security.access.annotation.Secured;
import org.springframework.stereotype.Controller;

import java.util.UUID;

@Controller
@RequiredArgsConstructor
class RoleController {

    private final RoleFacade roleFacade;

    @QueryMapping
    PageDto<RoleResponse> getAllRoles(@Argument final FilterRoleForm filterForm, @Argument final PageableRequest pageableRequest) {
        return roleFacade.getAllRoles(filterForm, pageableRequest);
    }

    @QueryMapping
    RoleResponse getRoleByName(@Argument final String name) {
        return roleFacade.findByName(name);
    }

    @Secured({"ROLE_ADMIN", "ROLE_SUPERADMIN"})
    @MutationMapping
    RoleResponse createRole(@Argument final CreateRoleForm createForm) {
        return roleFacade.createRole(createForm);
    }

    @Secured({"ROLE_ADMIN", "ROLE_SUPERADMIN"})
    @MutationMapping
    RoleResponse updateRole(@Argument final UUID uuid, @Argument final UpdateRoleForm updateForm) {
        return roleFacade.updateRole(uuid, updateForm);
    }

    @MutationMapping
    void deleteRole(@Argument final UUID uuid) {
        roleFacade.deleteRole(uuid);
    }
}
