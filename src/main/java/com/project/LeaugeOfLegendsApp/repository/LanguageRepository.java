package com.project.LeaugeOfLegendsApp.repository;

import java.util.Optional;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;
import com.project.LeaugeOfLegendsApp.model.ELanguage;
import com.project.LeaugeOfLegendsApp.model.Language;


@Repository
public interface LanguageRepository extends MongoRepository<Language, String> {
	
	Optional<Language> findByName(ELanguage lang);
 
}
