package com.project.LeaugeOfLegendsApp.shared;


import graphql.scalars.ExtendedScalars;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import graphql.schema.GraphQLScalarType;

import static com.project.LeaugeOfLegendsApp.shared.FileUploadScalar.FileUpload;

@Configuration
public class ScalarConfig {
	
	@Bean
	public GraphQLScalarType uploadScalar() {
		return FileUpload;
	}
	
	@Bean
	public GraphQLScalarType byteScalar() {
		return ExtendedScalars.GraphQLByte;
	}
	
	 
}
