package com.project.LeaugeOfLegendsApp.language;

import java.util.Optional;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface LanguageRepository extends MongoRepository<Language, String> {
	
	Optional<Language> findByName(ELanguage lang);
 
}
