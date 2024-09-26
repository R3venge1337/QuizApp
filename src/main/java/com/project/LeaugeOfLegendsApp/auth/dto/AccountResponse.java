package com.project.LeaugeOfLegendsApp.auth.dto;

import java.util.Set;
import java.util.UUID;

public record AccountResponse(UUID uuid, String username, String email, Set<RoleResponse> roles) {
}
