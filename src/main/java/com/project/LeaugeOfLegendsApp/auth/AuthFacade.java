package com.project.LeaugeOfLegendsApp.auth;

import com.project.LeaugeOfLegendsApp.auth.dto.CreateUserForm;
import com.project.LeaugeOfLegendsApp.auth.dto.JwtResponse;
import com.project.LeaugeOfLegendsApp.auth.dto.LoginRequest;
import com.project.LeaugeOfLegendsApp.auth.dto.UserResponse;
import com.project.LeaugeOfLegendsApp.auth.dto.VerifyUserForm;

public interface AuthFacade {

    JwtResponse authenticateUser(final LoginRequest loginRequest);

    UserResponse registerUser(final CreateUserForm createForm);

    void verifyUser(final VerifyUserForm verifyUserForm);

    void resendVerificationCode(final String email);
}
