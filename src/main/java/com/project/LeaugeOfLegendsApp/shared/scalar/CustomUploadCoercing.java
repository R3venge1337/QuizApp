package com.project.LeaugeOfLegendsApp.shared.scalar;

import graphql.GraphQLContext;
import graphql.execution.CoercedVariables;
import graphql.language.Value;
import graphql.schema.Coercing;
import graphql.schema.CoercingParseLiteralException;
import graphql.schema.CoercingParseValueException;
import graphql.schema.CoercingSerializeException;
import jakarta.annotation.Nullable;
import jakarta.servlet.http.Part;
import jakarta.validation.constraints.NotNull;
import lombok.NonNull;
import org.springframework.context.annotation.Configuration;

import java.io.IOException;
import java.util.Locale;

@Configuration
public class CustomUploadCoercing implements Coercing<Part, Void> {
    @Override
    public @Nullable Void serialize(@NonNull Object dataFetcherResult, @NonNull GraphQLContext graphQLContext, @NonNull Locale locale) throws CoercingSerializeException {
        throw new CoercingSerializeException("Upload is an input-only type");
    }

    @Override
    public @Nullable Part parseValue(@NonNull Object input, @NonNull GraphQLContext graphQLContext, @NonNull Locale locale) throws CoercingParseValueException {
        if (input instanceof Part) {
            Part part = (Part) input;
            try {
                String contentType = part.getContentType();
                byte[] content = new byte[part.getInputStream().available()];
                part.delete();
                return part;

            } catch (IOException e) {
                throw new CoercingParseValueException("Couldn't read content of the uploaded file");
            }
        } else if (null == input) {
            return null;
        } else {
            throw new CoercingParseValueException(
                    "Expected type " + Part.class.getName() + " but was " + input.getClass().getName());
        }
    }

    @Override
    public @Nullable Part parseLiteral(@NonNull Value<?> input, @NonNull CoercedVariables variables, @NonNull GraphQLContext graphQLContext, @NotNull Locale locale) throws CoercingParseLiteralException {
        throw new CoercingParseLiteralException(
                "Must use variables to specify Upload values");
    }
}
