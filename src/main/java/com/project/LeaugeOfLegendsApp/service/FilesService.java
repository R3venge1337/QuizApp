package com.project.LeaugeOfLegendsApp.service;

import java.io.IOException;

import javax.servlet.http.Part;

import org.bson.BsonBinarySubType;
import org.bson.types.Binary;
import org.bson.types.ObjectId;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.mongodb.gridfs.GridFsOperations;
import org.springframework.data.mongodb.gridfs.GridFsTemplate;
import org.springframework.stereotype.Service;

import com.mongodb.BasicDBObject;
import com.mongodb.DBObject;
import com.mongodb.client.gridfs.model.GridFSFile;
import com.project.LeaugeOfLegendsApp.model.Audio;
import com.project.LeaugeOfLegendsApp.model.Image;
import com.project.LeaugeOfLegendsApp.model.Video;
import com.project.LeaugeOfLegendsApp.repository.AudioRepository;
import com.project.LeaugeOfLegendsApp.repository.ImageRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class FilesService {
	
	    private final GridFsTemplate gridFsTemplate;

	    private final GridFsOperations operations;
	    
	    private final AudioRepository audioRepository;
	    
	    private final ImageRepository imageRepository;
	    
	    
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
}
