package com.project.LeaugeOfLegendsApp.category;

import graphql.kickstart.tools.GraphQLMutationResolver;
import lombok.RequiredArgsConstructor;
import org.springframework.security.access.annotation.Secured;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class CategoryMutation implements GraphQLMutationResolver {

    private final CategoryService categoryService;

    @Secured({"ROLE_ADMIN", "ROLE_SUPERADMIN"})
    public Category createCategory(Category category) {
        return categoryService.createCategory(category);
    }

}
