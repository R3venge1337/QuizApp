package com.project.LeaugeOfLegendsApp.category;

import java.util.List;

import org.springframework.stereotype.Service;

import com.project.LeaugeOfLegendsApp.exceptions.CategoryNotFoundException;

import lombok.RequiredArgsConstructor;
@Service
@RequiredArgsConstructor
public class CategoryService {
	
	private final CategoryRepository categoryRepository;
	
	public Category createCategory(final Category category) {
		return categoryRepository.insert(category);
	}
	
	public List<Category> findAllCategories(){
		return categoryRepository.findAll();
	}
	
	public Category getCategoryByName(final ECategory categoryName){
		return categoryRepository.getCategoryByName(categoryName)
					.orElseThrow(() -> new CategoryNotFoundException(categoryName.toString()));
	}
	


}
