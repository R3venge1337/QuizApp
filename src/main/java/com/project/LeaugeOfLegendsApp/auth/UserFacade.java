package com.project.LeaugeOfLegendsApp.auth;

import com.project.LeaugeOfLegendsApp.auth.dto.FilterUserForm;
import com.project.LeaugeOfLegendsApp.auth.dto.RoleUuidForm;
import com.project.LeaugeOfLegendsApp.auth.dto.UpdateUserForm;
import com.project.LeaugeOfLegendsApp.auth.dto.UserResponse;
import com.project.LeaugeOfLegendsApp.shared.controller.PageDto;
import com.project.LeaugeOfLegendsApp.shared.controller.PageableRequest;
import org.springframework.security.core.userdetails.UserDetailsService;

import java.util.UUID;

public interface UserFacade extends UserDetailsService {

    UserResponse updateUser(final UUID uuid, final UpdateUserForm updateForm);

    PageDto<UserResponse> getAllUsers(final FilterUserForm filterForm, final PageableRequest pageableRequest);

    UserResponse findByUsername(final String username);

    UserResponse findByUuid(final UUID uuid);

    void assignRolesToUser(final UUID userUuid, final RoleUuidForm uuids);

    void unassignRolesToUser(final UUID userUuid, final RoleUuidForm uuids);

    void deleteByUuid(final UUID uuid);
}