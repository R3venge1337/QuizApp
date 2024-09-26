package com.project.LeaugeOfLegendsApp.auth;

import com.project.LeaugeOfLegendsApp.auth.dto.CreateUserForm;
import com.project.LeaugeOfLegendsApp.auth.dto.JwtResponse;
import com.project.LeaugeOfLegendsApp.auth.dto.LoginRequest;
import com.project.LeaugeOfLegendsApp.auth.dto.UserResponse;

public interface AuthFacade {

    JwtResponse authenticateUser(final LoginRequest loginRequest) throws Exception;

    UserResponse registerUser(final CreateUserForm createForm);
}
