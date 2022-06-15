package com.project.LeaugeOfLegendsApp.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.project.LeaugeOfLegendsApp.model.Category;
import com.project.LeaugeOfLegendsApp.model.ECategory;
import com.project.LeaugeOfLegendsApp.repository.CategoryRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class CategoryService {
	
	private final CategoryRepository categoryRepository;
	
	public Category createCategory(Category category) {
		return categoryRepository.insert(category);
	}
	
	public List<Category> findAllCategories(){
		return categoryRepository.findAll();
	}
	
	public Category getCategoryByName(ECategory categoryName){
		return categoryRepository.getCategoryByName(categoryName).orElseThrow();
	}
	


}
