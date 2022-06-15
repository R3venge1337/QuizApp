package com.project.LeaugeOfLegendsApp.model;

import java.io.InputStream;

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
	
    private InputStream videoFile; 
}
