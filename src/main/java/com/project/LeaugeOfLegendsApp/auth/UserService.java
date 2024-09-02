package com.project.LeaugeOfLegendsApp.auth;

import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;

import com.project.LeaugeOfLegendsApp.exceptions.RoleNotFoundException;
import com.project.LeaugeOfLegendsApp.exceptions.UserNotFoundException;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class UserService {

	private final UserRepository userRepository;

	private final RoleRepository roleRepository;

	public User createUser(final User user) {
		return userRepository.insert(user);
	}

	public List<User> getAllUsers(int limit) {
		return userRepository.findAll().stream().limit(limit).collect(Collectors.toList());
	}

	public User findByUsername(final String username) {
		return userRepository.findByUsername(username).orElseThrow();
	}

	public User addRoleToUser(final String username, final Role role) {
		final User user = userRepository.findByUsername(username)
				.orElseThrow(() -> new UserNotFoundException("User not exist"));
		final Set<Role> roles = user.getRoles();
		roles.add(roleRepository.findByName(role.getName())
				.orElseThrow(() -> new RoleNotFoundException("Role not found")));
		return userRepository.save(user);
	}
	
	public User deleteUserRole(final String username, final Role role) {
		final User user = userRepository.findByUsername(username)
				.orElseThrow(() -> new UserNotFoundException("User not exist"));
		final Set<Role> roles = user.getRoles();
		roles.remove(roleRepository.findByName(role.getName())
				.orElseThrow(() -> new RoleNotFoundException("Role not found")));
		return userRepository.save(user);
	}

}
