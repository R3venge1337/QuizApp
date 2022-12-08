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

	public User createUser(User user) {
		return userRepository.insert(user);
	}

	public List<User> getAllUsers(int limit) {
		return userRepository.findAll().stream().limit(limit).collect(Collectors.toList());
	}

	public User findByUsername(String username) {
		return userRepository.findByUsername(username).orElseThrow();
	}

	public User addRoleToUser(String username, Role role) {
		User user = userRepository.findByUsername(username)
				.orElseThrow(() -> new UserNotFoundException("User not exist"));
		Set<Role> roles = user.getRoles();
		roles.add(roleRepository.findByName(role.getName())
				.orElseThrow(() -> new RoleNotFoundException("Role not found")));
		return userRepository.save(user);
	}
	
	public User deleteUserRole(String username,Role role) {
		User user = userRepository.findByUsername(username)
				.orElseThrow(() -> new UserNotFoundException("User not exist"));
		Set<Role> roles = user.getRoles();
		roles.remove(roleRepository.findByName(role.getName())
				.orElseThrow(() -> new RoleNotFoundException("Role not found")));
		return userRepository.save(user);
	}

}
