package com.project.LeaugeOfLegendsApp.shared;

import graphql.GraphQLContext;
import graphql.execution.CoercedVariables;
import graphql.language.StringValue;
import graphql.language.Value;
import graphql.schema.Coercing;
import graphql.schema.CoercingParseLiteralException;
import graphql.schema.CoercingParseValueException;
import graphql.schema.CoercingSerializeException;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import org.springframework.context.annotation.Configuration;

import java.util.Locale;
import java.util.regex.Pattern;

@Configuration
public class CustomEmailCoercing implements Coercing<Object, Object> {

    @Override
    public @Nullable Object serialize(@NotNull Object dataFetcherResult, @NotNull GraphQLContext graphQLContext, @NotNull Locale locale) throws CoercingSerializeException {
        return serializeEmail(dataFetcherResult);
    }

    @Override
    public @Nullable Object parseValue(@NotNull Object input, @NotNull GraphQLContext graphQLContext, @NotNull Locale locale) throws CoercingParseValueException {
        return parseEmailFromVariable(input);
    }

    @Override
    public @Nullable Object parseLiteral(@NotNull Value<?> input, @NotNull CoercedVariables variables, @NotNull GraphQLContext graphQLContext, @NotNull Locale locale) throws CoercingParseLiteralException {
        return parseEmailFromAstLiteral(input);
    }

    private boolean looksLikeAnEmailAddress(final String possibleEmailValue) {
        // ps.  I am not trying to replicate RFC-3696 clearly
        return Pattern.matches("[A-Za-z0-9]@[.*]", possibleEmailValue);
    }

    private Object serializeEmail(final Object dataFetcherResult) {
        String possibleEmailValue = String.valueOf(dataFetcherResult);
        if (looksLikeAnEmailAddress(possibleEmailValue)) {
            return possibleEmailValue;
        } else {
            throw new CoercingSerializeException("Unable to serialize " + possibleEmailValue + " as an email address");
        }
    }

    private Object parseEmailFromVariable(final Object input) {
        if (input instanceof String) {
            String possibleEmailValue = input.toString();
            if (looksLikeAnEmailAddress(possibleEmailValue)) {
                return possibleEmailValue;
            }
        }
        throw new CoercingParseValueException("Unable to parse variable value " + input + " as an email address");
    }

    private Object parseEmailFromAstLiteral(final Object input) {
        if (input instanceof StringValue) {
            String possibleEmailValue = ((StringValue) input).getValue();
            if (looksLikeAnEmailAddress(possibleEmailValue)) {
                return possibleEmailValue;
            }
        }
        throw new CoercingParseLiteralException(
                "Value is not any email address : '" + String.valueOf(input) + "'"
        );
    }
}
