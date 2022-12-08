package com.project.LeaugeOfLegendsApp.difficulty;

import org.springframework.security.access.annotation.Secured;
import org.springframework.stereotype.Component;

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
