package com.project.LeaugeOfLegendsApp.service;

import org.springframework.stereotype.Service;

import com.project.LeaugeOfLegendsApp.repository.RoleRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class RoleService {
	
	private final RoleRepository roleRepository;
}
