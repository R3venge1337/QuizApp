package com.project.LeaugeOfLegendsApp.model;


import org.bson.types.Binary;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import lombok.Data;
import lombok.NoArgsConstructor;

@Document(collection = "videos")
@Data
@NoArgsConstructor
public class Video {
	
	@Id
	private String id;
	
	private String videoName;
	
    private Binary videoFile;
    
	public Video(String videoName, Binary videoFile) {
		this.videoName = videoName;
		this.videoFile = videoFile;
	} 
    
    
}
