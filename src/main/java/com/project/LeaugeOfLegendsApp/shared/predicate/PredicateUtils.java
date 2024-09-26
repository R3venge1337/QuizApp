Apackage com.project.LeaugeOfLegendsApp.shared.predicate;


import com.querydsl.core.BooleanBuilder;
import com.querydsl.core.types.Predicate;
import com.querydsl.core.types.dsl.EntityPathBase;

import java.util.Collection;
import java.util.List;
import java.util.Objects;

import static java.util.Objects.nonNull;
import static org.apache.commons.lang3.ObjectUtils.isNotEmpty;

public final class PredicateUtils {

    private static final String LIKE_CHAR = "%";

    private PredicateUtils() {
    }

    public static Predicate buildAndPredicates(final BooleanBuilder builder, final Collection<Predicate> predicates) {
        return builder.andAnyOf(predicates.stream().filter(Objects::nonNull).toArray(Predicate[]::new)).getValue();
    }

    public static <T> void addEqualPredicate(final BooleanBuilder builder, final List<Predicate> predicates, final EntityPathBase<T> field,
                                             final T value) {
        if (nonNull(value)) {
            predicates.add(builder.and(field.eq(value)));
        }
    }

    public static <T> void addInPredicate(final BooleanBuilder builder, final Collection<Predicate> predicates, final EntityPathBase<T> fieldPath, final Collection<T> values) {
        if (isNotEmpty(values)) {
            predicates.add(builder.and(fieldPath.in(values)).getValue());
        }
    }
}