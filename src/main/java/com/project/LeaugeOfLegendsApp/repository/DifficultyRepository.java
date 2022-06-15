package com.project.LeaugeOfLegendsApp.repository;

import java.util.Optional;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import com.project.LeaugeOfLegendsApp.model.Difficulty;
import com.project.LeaugeOfLegendsApp.model.EDifficulty;

@Repository
public interface DifficultyRepository extends MongoRepository<Difficulty, String>  {
	Optional<Difficulty> findByName(EDifficulty difficulty);

}
