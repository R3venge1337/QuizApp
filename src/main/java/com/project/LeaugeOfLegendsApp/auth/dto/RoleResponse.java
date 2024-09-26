package com.project.LeaugeOfLegendsApp.auth.dto;

import java.util.Set;
import java.util.UUID;

public record RoleResponse(UUID uuid, String name, Set<PermissionResponse> permissions) {
}
