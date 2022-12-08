package com.project.LeaugeOfLegendsApp.file;


import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ImageRepository extends MongoRepository<Image, String> {

	Image findImageByImageName(String imageName);
}
