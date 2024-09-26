package com.project.LeaugeOfLegendsApp.auth.controller;

import com.project.LeaugeOfLegendsApp.auth.PermissionFacade;
import com.project.LeaugeOfLegendsApp.auth.dto.CreatePermissionForm;
import com.project.LeaugeOfLegendsApp.auth.dto.FilterPermissionForm;
import com.project.LeaugeOfLegendsApp.auth.dto.PermissionResponse;
import com.project.LeaugeOfLegendsApp.auth.dto.UpdatePermissionForm;
import com.project.LeaugeOfLegendsApp.shared.controller.PageDto;
import com.project.LeaugeOfLegendsApp.shared.controller.PageableRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.graphql.data.method.annotation.Argument;
import org.springframework.graphql.data.method.annotation.MutationMapping;
import org.springframework.graphql.data.method.annotation.QueryMapping;
import org.springframework.stereotype.Controller;

import java.util.UUID;

@Controller
@RequiredArgsConstructor
class PermissionController {

    private final PermissionFacade permissionFacade;

    @QueryMapping
    PageDto<PermissionResponse> getAllPermissions(@Argument final FilterPermissionForm filterForm, @Argument final PageableRequest pageableRequest) {
        return permissionFacade.findAllPermissions(filterForm, pageableRequest);
    }

    @QueryMapping
    PermissionResponse getPermissionByUuid(@Argument final UUID uuid) {
        return permissionFacade.findByUuid(uuid);
    }

    @MutationMapping
    PermissionResponse createPermission(@Argument final CreatePermissionForm createForm) {
        return permissionFacade.createPermission(createForm);
    }

    @MutationMapping
    PermissionResponse updatePermission(@Argument final UUID uuid, @Argument final UpdatePermissionForm updateForm) {
        return permissionFacade.updatePermission(uuid, updateForm);
    }

    @MutationMapping
    void deletePermission(@Argument final UUID uuid) {
        permissionFacade.deleteByUuid(uuid);
    }


}
