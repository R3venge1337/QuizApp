package com.project.LeaugeOfLegendsApp.shared;


import graphql.kickstart.servlet.apollo.ApolloScalars;
import graphql.scalars.ExtendedScalars;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import graphql.schema.GraphQLScalarType;

@Configuration
public class ScalarConfig {
	
	@Bean
	public GraphQLScalarType uploadScalar() {
		return ApolloScalars.Upload;
	}
	
	@Bean
	public GraphQLScalarType byteScalar() {
		return ExtendedScalars.GraphQLByte;
	}
	
	 
}
