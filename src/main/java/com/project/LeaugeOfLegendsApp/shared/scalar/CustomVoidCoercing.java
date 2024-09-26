package com.project.LeaugeOfLegendsApp.shared.scalar;

import graphql.GraphQLContext;
import graphql.execution.CoercedVariables;
import graphql.language.Value;
import graphql.schema.Coercing;
import graphql.schema.CoercingParseLiteralException;
import graphql.schema.CoercingParseValueException;
import graphql.schema.CoercingSerializeException;
import jakarta.annotation.Nullable;
import jakarta.validation.constraints.NotNull;
import lombok.NonNull;
import org.springframework.context.annotation.Configuration;

import java.util.Locale;

@Configuration
public class CustomVoidCoercing implements Coercing<Object, Object> {
    @Override
    public @Nullable Object serialize(@NonNull Object dataFetcherResult, @NonNull GraphQLContext graphQLContext, @NonNull Locale locale) throws CoercingSerializeException {
        return null;
    }

    @Override
    public @Nullable Object parseValue(@NonNull Object input, @NonNull GraphQLContext graphQLContext, @NonNull Locale locale) throws CoercingParseValueException {
        return null;
    }

    @Override
    public @Nullable Object parseLiteral(@NonNull Value<?> input, @NonNull CoercedVariables variables, @NonNull GraphQLContext graphQLContext, @NotNull Locale locale) throws CoercingParseLiteralException {
        return null;
    }
}
