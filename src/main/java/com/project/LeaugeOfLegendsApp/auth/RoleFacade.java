package com.project.LeaugeOfLegendsApp.auth;

import com.project.LeaugeOfLegendsApp.auth.dto.CreateRoleForm;
import com.project.LeaugeOfLegendsApp.auth.dto.FilterRoleForm;
import com.project.LeaugeOfLegendsApp.auth.dto.RoleResponse;
import com.project.LeaugeOfLegendsApp.auth.dto.UpdateRoleForm;
import com.project.LeaugeOfLegendsApp.shared.controller.PageDto;
import com.project.LeaugeOfLegendsApp.shared.controller.PageableRequest;

import java.util.UUID;

public interface RoleFacade {

    PageDto<RoleResponse> getAllRoles(final FilterRoleForm filterRoleForm, final PageableRequest pageableRequest);

    RoleResponse findByName(final String name);

    RoleResponse createRole(final CreateRoleForm createForm);

    RoleResponse updateRole(final UUID uuid, final UpdateRoleForm updateForm);

    void deleteRole(final UUID uuid);
}
