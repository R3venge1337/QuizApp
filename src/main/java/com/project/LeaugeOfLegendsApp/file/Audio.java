package com.project.LeaugeOfLegendsApp.file;


import org.bson.types.Binary;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Document(collection = "audios")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Audio {
	
	@Id
	private String id;
	
	private String audioName;
	
    private Binary audioFile;

	public Audio(final String audioName) {
		this.audioName = audioName;
	}

	public Audio(final String audioName, final Binary audioFile) {
		this.audioName = audioName;
		this.audioFile = audioFile;
	}
	
	
}
