package com.project.LeaugeOfLegendsApp.category;

import java.util.List;

import graphql.kickstart.tools.GraphQLQueryResolver;
import org.springframework.stereotype.Component;

import lombok.RequiredArgsConstructor;

@Component
@RequiredArgsConstructor
public class CategoryQuery implements GraphQLQueryResolver {
	
	private final CategoryService categoryService;
	
	public List<Category> findAllCategories(){
		return categoryService.findAllCategories();
	}
	
	public Category getCategoryByName(ECategory categoryName){
		return categoryService.getCategoryByName(categoryName);
	}
}
