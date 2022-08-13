package com.project.LeaugeOfLegendsApp.repository;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;
import org.springframework.stereotype.Repository;

import com.project.LeaugeOfLegendsApp.model.Audio;

@Repository
public interface AudioRepository extends MongoRepository<Audio, String> {
	
	@Query("{'audioName' : ?0}")
	Audio findAudioByAudioName(String audioName);

}
