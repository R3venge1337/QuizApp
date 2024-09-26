package com.project.LeaugeOfLegendsApp.shared.predicate;

import com.project.LeaugeOfLegendsApp.auth.domain.QRole;
import com.project.LeaugeOfLegendsApp.auth.dto.FilterRoleForm;
import com.querydsl.core.BooleanBuilder;
import com.querydsl.core.types.Predicate;
import com.querydsl.core.types.dsl.EntityPathBase;
import lombok.RequiredArgsConstructor;

import java.util.ArrayList;
import java.util.List;

import static com.project.LeaugeOfLegendsApp.shared.predicate.PredicateUtils.addEqualPredicate;
import static com.project.LeaugeOfLegendsApp.shared.predicate.PredicateUtils.buildAndPredicates;

@RequiredArgsConstructor
public class RolePredicateFactory {

    private final FilterRoleForm filterForm;

    public Predicate toPredicate() {
        final List<Predicate> predicates = new ArrayList<>();
        final BooleanBuilder booleanBuilder = new BooleanBuilder();

        addEqualPredicate(booleanBuilder, predicates, (EntityPathBase) QRole.role, filterForm.name());

        return buildAndPredicates(booleanBuilder, predicates);
    }
}
