package com.project.LeaugeOfLegendsApp.auth.domain;

import com.project.LeaugeOfLegendsApp.auth.AuthFacade;
import com.project.LeaugeOfLegendsApp.auth.UserFacade;
import com.project.LeaugeOfLegendsApp.auth.dto.CreateUserForm;
import com.project.LeaugeOfLegendsApp.auth.dto.JwtResponse;
import com.project.LeaugeOfLegendsApp.auth.dto.LoginRequest;
import com.project.LeaugeOfLegendsApp.auth.dto.UserResponse;
import com.project.LeaugeOfLegendsApp.auth.security.JwtTokenUtil;
import com.project.LeaugeOfLegendsApp.exceptions.AlreadyExistException;
import com.project.LeaugeOfLegendsApp.exceptions.NotFoundException;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

import static com.project.LeaugeOfLegendsApp.auth.domain.DomainMapper.mapToUserResponse;


@Service
@RequiredArgsConstructor
class AuthService implements AuthFacade {


    private final AuthenticationManager authenticationManager;
    private final UserRepository userRepository;
    private final AccountRepository accountRepository;
    private final RoleRepository roleRepository;
    private final PasswordEncoder passwordEncoder;
    private final JwtTokenUtil jwtTokenUtil;
    private final UserFacade userFacade;

    public JwtResponse authenticateUser(@Valid @RequestBody final LoginRequest loginRequest) throws Exception {
        Authentication auth = authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(loginRequest.username(), loginRequest.password()));

        //System.out.println("Auth: " + auth.getPrincipal());

        AuthorizationUser userDetails = (AuthorizationUser) userFacade.loadUserByUsername(loginRequest.username());

        String jwt = jwtTokenUtil.generateToken(userDetails);
		
		/*System.out.println("authenticateUser method userDetails:  " + userDetails.getUsername() + " " 
				+ userDetails.getPassword() + " " + userDetails.getEmail() );
				*/

        List<String> roles = userDetails.getAuthorities().stream().map(item -> item.getAuthority())
                .collect(Collectors.toList());


        SecurityContextHolder.getContext().setAuthentication(auth);

        return new JwtResponse(jwt);
    }

    public UserResponse registerUser(@Valid @RequestBody final CreateUserForm createForm) {
        if (accountRepository.existsByUsername(createForm.username())) {
            throw new AlreadyExistException("Error: Username is already taken!");
        }

        if (accountRepository.existsByEmail(createForm.email())) {
            throw new AlreadyExistException("Error: Email is already in use!");
        }

        Set<Role> roles = new HashSet<>();
        Role userRole = roleRepository.findByName("ROLE_USER")
                .orElseThrow(() -> new NotFoundException("Error: Role user is not found."));
        roles.add(userRole);

        // Create new user's account
        Account account = new Account(createForm.username(), createForm.email(),
                passwordEncoder.encode(createForm.password()), false, false);

        User user = new User(createForm.name(), createForm.secondName(), createForm.surname(), createForm.yearOfBirth(), account);

        return mapToUserResponse(userRepository.insert(user));
    }
}
