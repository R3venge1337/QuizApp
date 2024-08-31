package com.project.LeaugeOfLegendsApp.difficulty;

import java.util.List;

import graphql.kickstart.tools.GraphQLQueryResolver;
import org.springframework.stereotype.Component;

import lombok.RequiredArgsConstructor;

@Component
@RequiredArgsConstructor

public class DifficultyQuery implements GraphQLQueryResolver {
	
	private final DifficultyService difficultyService;
	
	List<Difficulty> findAllDifficulties(){
		return difficultyService.findAllDifficulties();
	}
	
	Difficulty getDifficultyByName(EDifficulty difficultyName) {
		return difficultyService.findDifficultyByName(difficultyName);
	}

}
