package com.project.LeaugeOfLegendsApp.shared.scalar;

import graphql.scalars.ExtendedScalars;
import graphql.schema.idl.RuntimeWiring.Builder;
import org.springframework.context.annotation.Configuration;
import org.springframework.graphql.execution.RuntimeWiringConfigurer;


@Configuration
public class ScalarWiringBuilder implements RuntimeWiringConfigurer {

    @Override
    public void configure(final Builder builder) {
        builder.scalar(ExtendedScalars.GraphQLByte)
                .scalar(GraphQLUploadScalar.FileUpload)
                .scalar(ExtendedScalars.UUID)
                .scalar(ExtendedScalars.GraphQLLong)
                .scalar(GraphQLVoidScalar.Void)
                .scalar(GraphQLEmailScalar.EMAIL)
                .scalar(ExtendedScalars.Date)
                .scalar(ExtendedScalars.DateTime)
                .build();
    }


}
