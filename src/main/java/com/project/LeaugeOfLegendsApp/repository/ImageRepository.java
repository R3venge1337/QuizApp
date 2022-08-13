package com.project.LeaugeOfLegendsApp.repository;


import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import com.project.LeaugeOfLegendsApp.model.Image;

@Repository
public interface ImageRepository extends MongoRepository<Image, String> {

	Image findImageByImageName(String imageName);
}
