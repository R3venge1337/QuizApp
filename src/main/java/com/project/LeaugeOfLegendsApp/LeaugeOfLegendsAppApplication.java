package com.project.LeaugeOfLegendsApp;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.mongodb.repository.config.EnableMongoRepositories;

@SpringBootApplication
@EnableMongoRepositories()
public class LeaugeOfLegendsAppApplication {

	public static void main(String[] args) {
		SpringApplication.run(LeaugeOfLegendsAppApplication.class, args);
	}
	
	/*
	@Autowired
	void setMapKeyDotReplacement(MappingMongoConverter mappingMongoConverter) {
	    mappingMongoConverter.setMapKeyDotReplacement("_");
	}
	*/
}
