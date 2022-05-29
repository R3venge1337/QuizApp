package com.project.LeaugeOfLegendsApp;

import java.util.List;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import com.project.LeaugeOfLegendsApp.model.Role;
import com.project.LeaugeOfLegendsApp.repository.RoleRepository;

import lombok.RequiredArgsConstructor;

@RestController
@RequiredArgsConstructor
public class RoleController {
	
	private final RoleRepository roleRepo;
	
	@GetMapping("/roles")
	public List<Role> getRoles(){
		return roleRepo.findAll();
	}
	
	@GetMapping("/roles/d")
	public Role getRoles3(){
		return roleRepo.findByName("ROLE_ADMIN").get();
	}

}
