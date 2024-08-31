package com.project.LeaugeOfLegendsApp.difficulty;

import graphql.kickstart.tools.GraphQLMutationResolver;
import org.springframework.security.access.annotation.Secured;
import org.springframework.stereotype.Component;

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
