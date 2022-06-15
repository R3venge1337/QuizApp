package com.project.LeaugeOfLegendsApp.service;

import java.io.IOException;

import org.bson.BsonBinarySubType;
import org.bson.types.Binary;
import org.bson.types.ObjectId;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.mongodb.gridfs.GridFsOperations;
import org.springframework.data.mongodb.gridfs.GridFsTemplate;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

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
	    
	    
	    public String addImage(String title, MultipartFile file) throws IOException { 
	        Image photo = new Image(title); 
	        photo.setImageFile(
	          new Binary(BsonBinarySubType.BINARY, file.getBytes())); 
	        photo = imageRepository.insert(photo); 
	        return photo.getId(); 
	    }

	    public Image getImage(String id) { 
	        return imageRepository.findById(id).get(); 
	    }
	    
	    public String addAudio(String title, MultipartFile file) throws IOException { 
	        Audio audioFile = new Audio(title); 
	        audioFile.setAudioFile(
	          new Binary(BsonBinarySubType.BINARY, file.getBytes())); 
	        audioFile = audioRepository.insert(audioFile); 
	        return audioFile.getId(); 
	    }

	    public Audio getAudio(String id) { 
	        return audioRepository.findById(id).get(); 
	    }
	    
	    public String addVideo(String title, MultipartFile file) throws IOException { 
	        DBObject metaData = new BasicDBObject(); 
	        metaData.put("type", "video"); 
	        metaData.put("title", title); 
	        ObjectId id = gridFsTemplate.store(
	          file.getInputStream(), file.getName(), file.getContentType(), metaData); 
	        return id.toString(); 
	    }
	    
	    public Video getVideo(String id) throws IllegalStateException, IOException { 
	        GridFSFile file = gridFsTemplate.findOne(new Query(Criteria.where("_id").is(id))); 
	        Video video = new Video(); 
	        video.setVideoName(file.getMetadata().get("title").toString()); 
	        video.setVideoFile(operations.getResource(file).getInputStream());
	        return video; 
	    }
}
