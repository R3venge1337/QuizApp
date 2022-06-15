package com.project.LeaugeOfLegendsApp.graphql;

import java.util.List;

import org.springframework.stereotype.Component;

import com.project.LeaugeOfLegendsApp.model.Difficulty;
import com.project.LeaugeOfLegendsApp.model.EDifficulty;
import com.project.LeaugeOfLegendsApp.service.DifficultyService;

import graphql.kickstart.tools.GraphQLQueryResolver;
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
