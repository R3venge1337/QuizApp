package com.project.LeaugeOfLegendsApp.file;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface VideoRepository extends MongoRepository<Video, String> {
	
	Video findVideoByVideoName(String videoName);

}
