package com.project.LeaugeOfLegendsApp.category;

import java.util.List;

import graphql.kickstart.annotations.GraphQLQueryResolver;
import org.springframework.stereotype.Component;

import lombok.RequiredArgsConstructor;

@Component
@RequiredArgsConstructor
@GraphQLQueryResolver
public class CategoryQuery  {
	
	private final CategoryService categoryService;
	
	public List<Category> findAllCategories(){
		return categoryService.findAllCategories();
	}
	
	public Category getCategoryByName(ECategory categoryName){
		return categoryService.getCategoryByName(categoryName);
	}
}
