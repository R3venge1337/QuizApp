package com.project.LeaugeOfLegendsApp.service;

import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

import javax.validation.Valid;

import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestBody;

import com.project.LeaugeOfLegendsApp.model.ERole;
import com.project.LeaugeOfLegendsApp.model.Role;
import com.project.LeaugeOfLegendsApp.model.User;
import com.project.LeaugeOfLegendsApp.repository.RoleRepository;
import com.project.LeaugeOfLegendsApp.repository.UserRepository;
import com.project.LeaugeOfLegendsApp.util.JwtResponse;
import com.project.LeaugeOfLegendsApp.util.JwtUtils;
import com.project.LeaugeOfLegendsApp.util.LoginRequest;
import com.project.LeaugeOfLegendsApp.util.MessageResponse;
import com.project.LeaugeOfLegendsApp.util.SignupRequest;
import com.project.LeaugeOfLegendsApp.util.UserDetailsImpl;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class AuthService {

	private final AuthenticationManager authenticationManager;
	private final UserRepository userRepository;
	private final RoleRepository roleRepository;
	private final PasswordEncoder encoder;
	private final JwtUtils jwtUtils;

	public ResponseEntity<?> authenticateUser(@Valid @RequestBody LoginRequest loginRequest) {
		Authentication authentication = authenticationManager.authenticate(
				new UsernamePasswordAuthenticationToken(loginRequest.getUsername(), loginRequest.getPassword()));
		SecurityContextHolder.getContext().setAuthentication(authentication);
		String jwt = jwtUtils.generateJwtToken(authentication);

		UserDetailsImpl userDetails = (UserDetailsImpl) authentication.getPrincipal();
		List<String> roles = userDetails.getAuthorities().stream().map(item -> item.getAuthority())
				.collect(Collectors.toList());
		return ResponseEntity.ok(
				new JwtResponse(jwt, userDetails.getId(), userDetails.getUsername(), userDetails.getEmail(), roles));
	}

	public ResponseEntity<?> registerUser(@Valid @RequestBody SignupRequest signUpRequest) {
		if (userRepository.existsByUsername(signUpRequest.getUsername())) {
			return ResponseEntity.badRequest().body(new MessageResponse("Error: Username is already taken!"));
		}

		if (userRepository.existsByEmail(signUpRequest.getEmail())) {
			return ResponseEntity.badRequest().body(new MessageResponse("Error: Email is already in use!"));
		}
		// Create new user's account
		User user = new User(signUpRequest.getUsername(), signUpRequest.getEmail(),
				encoder.encode(signUpRequest.getPassword()));

		Set<Role> roles = new HashSet<>();
		Role userRole = roleRepository.findByName(ERole.ROLE_USER)
				.orElseThrow(() -> new RuntimeException("Error: Role user is not found."));
		roles.add(userRole);

		/*
		 * if (strRoles == null) { Role userRole =
		 * roleRepository.findByName("ROLE_USER") .orElseThrow(() -> new
		 * RuntimeException("Error: Role is not found.")); roles.add(userRole); } else {
		 * strRoles.forEach(role -> { switch (role.getName()) { case "admin": Role
		 * adminRole = roleRepository.findByName("ROLE_ADMIN") .orElseThrow(() -> new
		 * RuntimeException("Error: Role is not found.")); roles.add(adminRole); break;
		 * case "mod": Role modRole = roleRepository.findByName("ROLE_MOD")
		 * .orElseThrow(() -> new RuntimeException("Error: Role is not found."));
		 * roles.add(modRole); break; default: Role userRole =
		 * roleRepository.findByName("ROLE_USER") .orElseThrow(() -> new
		 * RuntimeException("Error: Role is not found.")); roles.add(userRole); } }); }
		 */
		user.setRoles(roles);
		userRepository.insert(user);
		return ResponseEntity.ok(new MessageResponse("User registered successfully!"));

	}

}
