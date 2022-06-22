package com.project.LeaugeOfLegendsApp.scalars;



import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import graphql.kickstart.servlet.apollo.ApolloScalars;

import graphql.schema.GraphQLScalarType;

@Configuration
public class ScalarConfig {

	@Bean
	public GraphQLScalarType uploadScalar() {
		return ApolloScalars.Upload;
	}
	
}
