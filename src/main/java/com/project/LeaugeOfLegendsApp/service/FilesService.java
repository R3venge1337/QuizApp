package com.project.LeaugeOfLegendsApp.service;

import java.io.IOException;

import javax.servlet.http.Part;

import org.bson.BsonBinarySubType;
import org.bson.types.Binary;
import org.springframework.stereotype.Service;

import com.project.LeaugeOfLegendsApp.model.Audio;
import com.project.LeaugeOfLegendsApp.model.Image;
import com.project.LeaugeOfLegendsApp.model.Video;
import com.project.LeaugeOfLegendsApp.repository.AudioRepository;
import com.project.LeaugeOfLegendsApp.repository.ImageRepository;
import com.project.LeaugeOfLegendsApp.repository.VideoRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class FilesService {
	
	    //private final GridFsTemplate gridFsTemplate;

	   // private final GridFsOperations operations;
	    
	    private final AudioRepository audioRepository;
	    
	    private final ImageRepository imageRepository;
	    
	    private final VideoRepository videoRepository;
	    
	    //,audioFile: Upload,imageFile: Upload,videoFile: Upload
	    
	    public String addImage(Part file) throws IOException { 
	    	Image photo = new Image(file.getSubmittedFileName(),new Binary(BsonBinarySubType.BINARY, file.getInputStream().readAllBytes()));
	        photo = imageRepository.insert(photo); 
	        return photo.getId();
	    	
	    }

	    public Image getImage(String id) { 
	        return imageRepository.findById(id).get(); 
	    }
	    
	    public String addAudio(Part file) throws IOException { 
	        Audio audioFile = new Audio(file.getSubmittedFileName(),new Binary(BsonBinarySubType.BINARY, file.getInputStream().readAllBytes())); 
	        audioFile = audioRepository.insert(audioFile); 
	        return audioFile.getId(); 
	    }

	    public Audio getAudio(String id) { 
	        return audioRepository.findById(id).get(); 
	    }
	    
	    /*
	    public String addVideo(Part file) throws IOException { 
	        DBObject metaData = new BasicDBObject(); 
	        metaData.put("type", "video"); 
	        ObjectId id = gridFsTemplate.store(
	          file.getInputStream(), file.getSubmittedFileName(), file.getContentType(), metaData); 
	        return id.toString(); 
	    }
	    
	    public Video getVideo(String id) throws IllegalStateException, IOException { 
	        GridFSFile file = gridFsTemplate.findOne(new Query(Criteria.where("_id").is(id))); 
	        Video video = new Video(); 
	        video.setId(file.getId().toString());
	        video.setVideoName(file.getFilename()); 
	        video.setVideoFile(operations.getResource(file).getInputStream());
	        return video; 
	    }
	    */
	    
	    public String addVideo(Part file) throws IOException { 
	    	Video video = new Video(file.getSubmittedFileName(),new Binary(BsonBinarySubType.BINARY, file.getInputStream().readAllBytes()));
	        video = videoRepository.insert(video); 
	        return video.getId();
	    }
	    
	    public Video getVideo(String id) throws IllegalStateException, IOException { 
	    	return videoRepository.findById(id).get();
	    }
	    
	    public Video getVideoByName(String name) throws IllegalStateException, IOException { 
	       return videoRepository.findVideoByVideoName(name);
	    }
}
