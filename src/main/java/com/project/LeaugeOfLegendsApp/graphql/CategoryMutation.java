package com.project.LeaugeOfLegendsApp.graphql;

import org.springframework.stereotype.Component;

import com.project.LeaugeOfLegendsApp.model.Category;
import com.project.LeaugeOfLegendsApp.service.CategoryService;

import graphql.kickstart.tools.GraphQLMutationResolver;
import lombok.RequiredArgsConstructor;

@Component
@RequiredArgsConstructor
public class CategoryMutation implements GraphQLMutationResolver  {
	
	private final CategoryService categoryService;
	
	public Category createCategory(Category category) {
		return categoryService.createCategory(category);
	}

}
