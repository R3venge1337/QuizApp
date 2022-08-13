package com.project.LeaugeOfLegendsApp.repository;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import com.project.LeaugeOfLegendsApp.model.Video;

@Repository
public interface VideoRepository extends MongoRepository<Video, String> {
	
	Video findVideoByVideoName(String videoName);

}
