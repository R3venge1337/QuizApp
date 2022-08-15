package com.project.LeaugeOfLegendsApp.graphql;

import org.springframework.security.access.annotation.Secured;
import org.springframework.stereotype.Component;

import com.project.LeaugeOfLegendsApp.model.Difficulty;
import com.project.LeaugeOfLegendsApp.service.DifficultyService;

import graphql.kickstart.tools.GraphQLMutationResolver;
import lombok.RequiredArgsConstructor;

@Component
@RequiredArgsConstructor
public class DifficultyMutation implements GraphQLMutationResolver {
	
	private final DifficultyService difficultyService;
	
	@Secured({"ROLE_ADMIN", "ROLE_SUPERADMIN"})
	public Difficulty createDifficulty(Difficulty difficulty) {
		return difficultyService.createDifficulty(difficulty);
		
	}
}
