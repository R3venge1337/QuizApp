package com.project.LeaugeOfLegendsApp.file;

import java.io.IOException;

import graphql.kickstart.tools.GraphQLQueryResolver;
import org.springframework.stereotype.Component;

import lombok.RequiredArgsConstructor;

@Component
@RequiredArgsConstructor
public class FilesQuery implements GraphQLQueryResolver {
	
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
