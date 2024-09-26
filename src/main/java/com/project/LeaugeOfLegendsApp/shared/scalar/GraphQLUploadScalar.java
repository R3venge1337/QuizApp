package com.project.LeaugeOfLegendsApp.shared.scalar;

import graphql.schema.GraphQLScalarType;

public class GraphQLUploadScalar {
    public static final GraphQLScalarType FileUpload = GraphQLScalarType.newScalar()
            .name("Upload")
            .coercing(new CustomUploadCoercing())
            .build();
}


