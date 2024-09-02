package com.project.LeaugeOfLegendsApp.file;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface AudioRepository extends MongoRepository<Audio, String> {
	
	@Query("{'audioName' : ?0}")
	Audio findAudioByAudioName(final String audioName);

}
