package com.project.LeaugeOfLegendsApp.shared;

import graphql.scalars.ExtendedScalars;
import graphql.schema.idl.RuntimeWiring.Builder;
import org.springframework.context.annotation.Configuration;
import org.springframework.graphql.execution.RuntimeWiringConfigurer;

import static com.project.LeaugeOfLegendsApp.shared.FileUploadScalar.FileUpload;

@Configuration
public class ScalarWiringBuilder implements RuntimeWiringConfigurer {

    @Override
    public void configure(final Builder builder) {
        builder.scalar(ExtendedScalars.GraphQLByte)
                .scalar(FileUpload)
                .build();
    }


}
