package com.project.LeaugeOfLegendsApp.auth;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class RoleService {
	
	private final RoleRepository roleRepository;
	
	public Optional<Role> findByName(final ERole name) {
		return roleRepository.findByName(name);
		
	}
	
	public List<Role> findAllRoles() {
		return roleRepository.findAll();
		
	}
	
	public Role createRole(final Role role) {
		return roleRepository.insert(role);
	}
}
