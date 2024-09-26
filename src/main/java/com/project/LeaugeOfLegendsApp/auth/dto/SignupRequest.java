package com.project.LeaugeOfLegendsApp.auth.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.RequiredArgsConstructor;

import java.util.Set;


@RequiredArgsConstructor
public class SignupRequest {

    @NotBlank
    @Size(min = 3, max = 20)
    private String username;

    @NotBlank
    @Size(max = 50)
    @Email
    private String email;

    private Set<RoleResponse> roles;

    @NotBlank
    @Size(min = 8, max = 40)
    private String password;

}
