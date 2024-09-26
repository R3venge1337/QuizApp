package com.project.LeaugeOfLegendsApp.auth.dto;

import java.util.UUID;

public record UserResponse(UUID uuid, String name, String secondName, String surname, Short yearOfBirth,
                           AccountResponse account) {
}
