package com.project.LeaugeOfLegendsApp.difficulty;

import java.util.Optional;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface DifficultyRepository extends MongoRepository<Difficulty, String>  {
	Optional<Difficulty> findByName(final EDifficulty difficulty);

}
