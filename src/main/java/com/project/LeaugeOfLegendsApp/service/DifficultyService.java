package com.project.LeaugeOfLegendsApp.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.project.LeaugeOfLegendsApp.model.Difficulty;
import com.project.LeaugeOfLegendsApp.model.EDifficulty;
import com.project.LeaugeOfLegendsApp.repository.DifficultyRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class DifficultyService {

	private final DifficultyRepository difficultyRepository;

	public Difficulty createDifficulty(Difficulty difficulty) {
		return difficultyRepository.insert(difficulty);
	}

	public Difficulty findDifficultyByName(EDifficulty difficulty) {
		return difficultyRepository.findByName(difficulty).orElseThrow();
	}

	public List<Difficulty> findAllDifficulties() {
		return difficultyRepository.findAll();
	}

}
