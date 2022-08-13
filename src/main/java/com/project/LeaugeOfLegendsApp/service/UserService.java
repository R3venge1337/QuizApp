package com.project.LeaugeOfLegendsApp.service;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;

import com.project.LeaugeOfLegendsApp.model.User;
import com.project.LeaugeOfLegendsApp.repository.UserRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class UserService {
	
	private final UserRepository userRepository;
		
	public User createUser(User user) {
		return userRepository.insert(user);
	}
	
	public List<User> getAllUsers(int limit) {
		return userRepository.findAll().stream().limit(limit).collect(Collectors.toList());
	}
	
	public User findByUsername(String username) {
		return userRepository.findByUsername(username).orElseThrow();
	}
}
