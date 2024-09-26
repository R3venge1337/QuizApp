package com.project.LeaugeOfLegendsApp.shared.scalar;

import graphql.schema.GraphQLScalarType;

public class GraphQLEmailScalar {
    public static final GraphQLScalarType EMAIL = GraphQLScalarType.newScalar()
            .name("email")
            .description("A custom scalar that handles emails")
            .coercing(new CustomEmailCoercing())
            .build();
}