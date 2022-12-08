package com.project.LeaugeOfLegendsApp.category;

import java.util.Optional;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CategoryRepository  extends MongoRepository<Category, String> {
	
	Optional<Category> getCategoryByName(ECategory categoryName);
		
}
