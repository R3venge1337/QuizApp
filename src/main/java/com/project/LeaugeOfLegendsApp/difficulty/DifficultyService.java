package com.project.LeaugeOfLegendsApp.difficulty;

import java.util.List;

import org.springframework.stereotype.Service;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class DifficultyService {

	private final DifficultyRepository difficultyRepository;

	public Difficulty createDifficulty(final Difficulty difficulty) {
		return difficultyRepository.insert(difficulty);
	}

	public Difficulty findDifficultyByName(final EDifficulty difficulty) {
		return difficultyRepository.findByName(difficulty).orElseThrow();
	}

	public List<Difficulty> findAllDifficulties() {
		return difficultyRepository.findAll();
	}

}
