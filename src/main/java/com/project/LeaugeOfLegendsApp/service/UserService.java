package com.project.LeaugeOfLegendsApp.service;

import org.springframework.stereotype.Service;

import com.project.LeaugeOfLegendsApp.repository.UserRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class UserService {
	
	private final UserRepository userRepository;

}
