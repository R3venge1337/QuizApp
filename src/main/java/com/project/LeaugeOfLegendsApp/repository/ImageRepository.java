package com.project.LeaugeOfLegendsApp.repository;


import org.springframework.data.mongodb.repository.MongoRepository;

import com.project.LeaugeOfLegendsApp.model.Image;

public interface ImageRepository extends MongoRepository<Image, String> {

	Image findImageByImageName(String imageName);
}
