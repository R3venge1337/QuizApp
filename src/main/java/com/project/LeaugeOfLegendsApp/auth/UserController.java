package com.project.LeaugeOfLegendsApp.auth;

import lombok.RequiredArgsConstructor;
import org.springframework.graphql.data.method.annotation.Argument;
import org.springframework.graphql.data.method.annotation.MutationMapping;
import org.springframework.graphql.data.method.annotation.QueryMapping;
import org.springframework.security.access.annotation.Secured;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;

import java.util.List;

@Controller
@RequiredArgsConstructor
class UserController {

    private final UserService userService;
    private final AuthService authService;

    @QueryMapping
    List<User> getAllUsers(@Argument int limit) {
        return userService.getAllUsers(limit);
    }

    @QueryMapping
    User findByUsername(@Argument final String username) {
        return userService.findByUsername(username);
    }

    @MutationMapping
    User createUser(@Argument final User user) {
        return userService.createUser(user);
    }

    @MutationMapping
    String registerUser(@Argument final SignupRequest signupRequest) {
        return authService.registerUser(signupRequest).getBody().toString();
    }

    @PreAuthorize("isAnonymous()")
    @MutationMapping
    JwtResponse authenticateUser(@Argument final LoginRequest loginRequest) throws Exception {
        return (JwtResponse) authService.authenticateUser(loginRequest).getBody();

    }

    @Secured({"ROLE_ADMIN", "ROLE_SUPERADMIN"})
    @MutationMapping
    User addRoleToUser(@Argument final String username, @Argument final Role role) {
        return userService.addRoleToUser(username, role);
    }

    @Secured({"ROLE_ADMIN", "ROLE_SUPERADMIN"})
    @MutationMapping
    User deleteUserRole(@Argument final String username, @Argument final Role role) {
        return userService.deleteUserRole(username, role);
    }
}
