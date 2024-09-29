package com.project.LeaugeOfLegendsApp.auth.domain;

import com.project.LeaugeOfLegendsApp.auth.dto.AccountResponse;
import com.project.LeaugeOfLegendsApp.auth.dto.PermissionResponse;
import com.project.LeaugeOfLegendsApp.auth.dto.RoleResponse;
import com.project.LeaugeOfLegendsApp.auth.dto.UserResponse;
import com.project.LeaugeOfLegendsApp.auth.dto.UserWithAccount;

import java.util.stream.Collectors;

class DomainMapper {
    public static UserResponse mapToUserResponse(final User user) {
        return new UserResponse(user.getUuid(), user.getName(), user.getSecondName(), user.getSurname(), user.getYearOfBirth(), mapToAccountResponse(user.getAccount()));
    }

    public static UserWithAccount maptoUserWithAccount(final User user) {
        final Account account = user.getAccount();
        return new UserWithAccount(user.getUuid(), account.getUuid(), account.getUsername(), account.getPassword(), account.getEmail(), account.getRoles().stream().map(Role::getName).collect(Collectors.toSet()), account.getIsEnabled(), account.getIsLocked());
    }

    public static AccountResponse mapToAccountResponse(final Account account) {
        return new AccountResponse(account.getUuid(), account.getUsername(), account.getEmail(), account.getIsEnabled(), account.getRoles().stream().map(DomainMapper::mapToRoleResponse).collect(Collectors.toUnmodifiableSet()));
    }

    public static RoleResponse mapToRoleResponse(final Role role) {
        return new RoleResponse(role.getUuid(), role.getName(), role.getPermissions().stream().map(DomainMapper::mapToPermissionResponse).collect(Collectors.toUnmodifiableSet()));
    }

    public static PermissionResponse mapToPermissionResponse(final Permission permission) {
        return new PermissionResponse(permission.getUuid(), permission.getName());
    }
}
