package com.project.LeaugeOfLegendsApp.category;

import graphql.kickstart.annotations.GraphQLMutationResolver;
import org.springframework.security.access.annotation.Secured;
import org.springframework.stereotype.Component;

import lombok.RequiredArgsConstructor;

@Component
@RequiredArgsConstructor
@GraphQLMutationResolver
public class CategoryMutation  {
	
	private final CategoryService categoryService;
	
	@Secured({"ROLE_ADMIN", "ROLE_SUPERADMIN"})
	public Category createCategory(Category category) {
		return categoryService.createCategory(category);
	}

}
