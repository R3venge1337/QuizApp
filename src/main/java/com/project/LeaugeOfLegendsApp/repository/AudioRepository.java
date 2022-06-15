package com.project.LeaugeOfLegendsApp.repository;

import org.springframework.data.mongodb.repository.MongoRepository;

import com.project.LeaugeOfLegendsApp.model.Audio;

public interface AudioRepository extends MongoRepository<Audio, String> {

}
