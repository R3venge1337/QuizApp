package com.project.LeaugeOfLegendsApp.model;



import org.bson.types.Binary;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import lombok.Data;
import lombok.NoArgsConstructor;

@Document(collection = "audios")
@Data
@NoArgsConstructor
public class Audio {
	
	@Id
	private String id;
	
	private String audioName;
	
    private Binary audioFile;

	public Audio(String audioName) {
		this.audioName = audioName;
	}
}
