package com.project.LeaugeOfLegendsApp.shared;

import graphql.schema.GraphQLScalarType;

public class EmailScalar {
    public static final GraphQLScalarType EMAIL = GraphQLScalarType.newScalar()
            .name("email")
            .description("A custom scalar that handles emails")
            .coercing(new CustomEmailCoercing())
            .build();
}