package com.project.LeaugeOfLegendsApp.auth.controller;

import com.project.LeaugeOfLegendsApp.auth.AuthFacade;
import com.project.LeaugeOfLegendsApp.auth.dto.CreateUserForm;
import com.project.LeaugeOfLegendsApp.auth.dto.JwtResponse;
import com.project.LeaugeOfLegendsApp.auth.dto.LoginRequest;
import com.project.LeaugeOfLegendsApp.auth.dto.UserResponse;
import com.project.LeaugeOfLegendsApp.auth.dto.VerifyUserForm;
import lombok.RequiredArgsConstructor;
import org.springframework.graphql.data.method.annotation.Argument;
import org.springframework.graphql.data.method.annotation.MutationMapping;
import org.springframework.stereotype.Controller;

@Controller
@RequiredArgsConstructor
class AuthController {

    private final AuthFacade authFacade;

    @MutationMapping
    UserResponse registerUser(@Argument final CreateUserForm createForm) {
        return authFacade.registerUser(createForm);
    }

    @MutationMapping
    JwtResponse authenticateUser(@Argument final LoginRequest loginRequest) {
        return authFacade.authenticateUser(loginRequest);
    }

    @MutationMapping
    void verifyUser(@Argument final VerifyUserForm verifyUserForm) {
        authFacade.verifyUser(verifyUserForm);
    }

    @MutationMapping
    void resendVerificationCode(@Argument final String email) {
        authFacade.resendVerificationCode(email);
    }
}
