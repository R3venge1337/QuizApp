package com.project.LeaugeOfLegendsApp.auth;

import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;


import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.DisabledException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestBody;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class AuthService {
	
	
	private final AuthenticationManager authenticationManager;
	
	private final UserRepository userRepository;
	private final RoleRepository roleRepository;
	private final PasswordEncoder encoder;
	private final UserDetailsServiceImpl userDetailsService;
	private final JwtTokenUtil jwtTokenUtil;


	public ResponseEntity<?> authenticateUser(@Valid @RequestBody final LoginRequest loginRequest) throws Exception {
		
		authenticate(loginRequest.getUsername(),loginRequest.getPassword());
		
		Authentication auth = authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(loginRequest.getUsername(),loginRequest.getPassword()));
		
		//System.out.println("Auth: " + auth.getPrincipal());

		CustomUserDetails userDetails =  (CustomUserDetails) userDetailsService.loadUserByUsername(loginRequest.getUsername());
		
		String jwt = jwtTokenUtil.generateToken(userDetails);
		
		/*System.out.println("authenticateUser method userDetails:  " + userDetails.getUsername() + " " 
				+ userDetails.getPassword() + " " + userDetails.getEmail() );
				*/
		
		List<String> roles = userDetails.getAuthorities().stream().map(item -> item.getAuthority())
				.collect(Collectors.toList());
		
		
		SecurityContextHolder.getContext().setAuthentication(auth);
		
		return ResponseEntity.ok(
				new JwtResponse(jwt, userDetails.getId(), userDetails.getUsername(), userDetails.getEmail(), roles));
	}

	public ResponseEntity<?> registerUser(@Valid @RequestBody final SignupRequest signUpRequest) {
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

		user.setRoles(roles);
		userRepository.insert(user);
		return ResponseEntity.ok(new MessageResponse("User registered successfully!"));
	}
	
	private void authenticate(final String username, final String password) throws Exception {
		try {
			authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(username, password));
		} catch (DisabledException e) {
			throw new Exception("USER_DISABLED", e);
		} catch (BadCredentialsException e) {
			throw new Exception("INVALID_CREDENTIALS", e);
		}
	}
}
