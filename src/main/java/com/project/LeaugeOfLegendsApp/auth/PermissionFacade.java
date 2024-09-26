package com.project.LeaugeOfLegendsApp.auth;

import com.project.LeaugeOfLegendsApp.auth.dto.CreatePermissionForm;
import com.project.LeaugeOfLegendsApp.auth.dto.FilterPermissionForm;
import com.project.LeaugeOfLegendsApp.auth.dto.PermissionResponse;
import com.project.LeaugeOfLegendsApp.auth.dto.UpdatePermissionForm;
import com.project.LeaugeOfLegendsApp.shared.controller.PageDto;
import com.project.LeaugeOfLegendsApp.shared.controller.PageableRequest;

import java.util.UUID;

public interface PermissionFacade {

    PageDto<PermissionResponse> findAllPermissions(final FilterPermissionForm filterForm, final PageableRequest pageableRequest);

    PermissionResponse findByUuid(final UUID uuid);

    PermissionResponse createPermission(final CreatePermissionForm createForm);

    PermissionResponse updatePermission(final UUID uuid, final UpdatePermissionForm updateForm);

    void deleteByUuid(final UUID uuid);
}
