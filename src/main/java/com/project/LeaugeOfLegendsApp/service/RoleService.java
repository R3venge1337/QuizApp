package com.project.LeaugeOfLegendsApp.service;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import com.project.LeaugeOfLegendsApp.model.Role;
import com.project.LeaugeOfLegendsApp.repository.RoleRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class RoleService {
	
	private final RoleRepository roleRepository;
	
	public Optional<Role> findByName(String name) {
		return roleRepository.findByName(name);
		
	}
	
	public List<Role> findAllRoles() {
		return roleRepository.findAll();
		
	}
	
	public Role createRole(Role role) {
		return roleRepository.insert(role);
	}
}
