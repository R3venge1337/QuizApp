package com.project.LeaugeOfLegendsApp.repository;

import org.springframework.data.mongodb.repository.MongoRepository;

import com.project.LeaugeOfLegendsApp.model.Video;

public interface VideoRepository extends MongoRepository<Video, String> {
	
	Video findVideoByVideoName(String videoName);

}
