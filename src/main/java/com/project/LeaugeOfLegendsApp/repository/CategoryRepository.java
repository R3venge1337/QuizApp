package com.project.LeaugeOfLegendsApp.repository;

import java.util.Optional;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import com.project.LeaugeOfLegendsApp.model.Category;
import com.project.LeaugeOfLegendsApp.model.ECategory;

@Repository
public interface CategoryRepository  extends MongoRepository<Category, String> {
	
	Optional<Category> getCategoryByName(ECategory categoryName);
		
}
