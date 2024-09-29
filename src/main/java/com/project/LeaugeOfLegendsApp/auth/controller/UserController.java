package com.project.LeaugeOfLegendsApp.auth.controller;

import com.project.LeaugeOfLegendsApp.auth.UserFacade;
import com.project.LeaugeOfLegendsApp.auth.dto.FilterUserForm;
import com.project.LeaugeOfLegendsApp.auth.dto.RoleUuidForm;
import com.project.LeaugeOfLegendsApp.auth.dto.UserResponse;
import com.project.LeaugeOfLegendsApp.auth.dto.UserWithAccount;
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
class UserController {

    private final UserFacade userFacade;

    @QueryMapping
    PageDto<UserResponse> getAllUsers(@Argument FilterUserForm filterForm, @Argument final PageableRequest pageableRequest) {
        return userFacade.getAllUsers(filterForm, pageableRequest);
    }

    @QueryMapping
    UserWithAccount findByUsername(@Argument final String username) {
        return userFacade.findByUsername(username);
    }

    @QueryMapping
    UserResponse findUserByUuid(@Argument final UUID uuid) {
        return userFacade.findByUuid(uuid);
    }

    @Secured({"ROLE_ADMIN", "ROLE_SUPERADMIN"})
    @MutationMapping
    void addRoleToUser(@Argument final UUID uuid, @Argument final RoleUuidForm uuids) {
        userFacade.assignRolesToUser(uuid, uuids);
    }

    @Secured({"ROLE_ADMIN", "ROLE_SUPERADMIN"})
    @MutationMapping
    void deleteUserRole(@Argument final UUID uuid, @Argument final RoleUuidForm uuids) {
        userFacade.unassignRolesToUser(uuid, uuids);
    }

    @Secured({"ROLE_ADMIN", "ROLE_SUPERADMIN"})
    @MutationMapping
    void deleteUser(@Argument final UUID uuid) {
        userFacade.deleteByUuid(uuid);
    }
}
