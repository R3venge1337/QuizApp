package com.project.LeaugeOfLegendsApp.shared.scalar;

import graphql.schema.GraphQLScalarType;

public class GraphQLVoidScalar {

    public static final GraphQLScalarType Void = GraphQLScalarType.newScalar()
            .name("Void")
            .description("A custom scalar that represents the null value")
            .coercing(new CustomVoidCoercing())
            .build();
}