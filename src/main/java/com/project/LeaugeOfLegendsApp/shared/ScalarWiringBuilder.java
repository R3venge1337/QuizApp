package com.project.LeaugeOfLegendsApp.shared;

import org.springframework.context.annotation.Configuration;
import org.springframework.graphql.execution.RuntimeWiringConfigurer;

import graphql.kickstart.servlet.apollo.ApolloScalars;
import graphql.scalars.ExtendedScalars;
import graphql.schema.idl.RuntimeWiring.Builder;

@Configuration
public class ScalarWiringBuilder implements RuntimeWiringConfigurer{

	@Override
	public void configure(Builder builder) {
		builder.scalar(ExtendedScalars.GraphQLByte)
		.scalar(ApolloScalars.Upload)
		.build();
	}

}
