package com.project.LeaugeOfLegendsApp.graphql;

import java.io.IOException;

import org.springframework.stereotype.Component;

import com.project.LeaugeOfLegendsApp.model.Audio;
import com.project.LeaugeOfLegendsApp.model.Image;
import com.project.LeaugeOfLegendsApp.model.Video;
import com.project.LeaugeOfLegendsApp.service.FilesService;

import graphql.kickstart.tools.GraphQLQueryResolver;
import lombok.RequiredArgsConstructor;

@Component
@RequiredArgsConstructor
public class FilesQuery implements GraphQLQueryResolver{
	
	private final FilesService fileService;
	
	public Image getImage(String id) {
		return fileService.getImage(id);
	}
	public Audio getAudio(String id) {
		return fileService.getAudio(id);
	}
	public Video getVideo(String id) throws IllegalStateException, IOException {
		return fileService.getVideo(id);
	}

}
