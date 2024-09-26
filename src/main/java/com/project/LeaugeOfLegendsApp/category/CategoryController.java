package com.project.LeaugeOfLegendsApp.category;

import lombok.RequiredArgsConstructor;
import org.springframework.graphql.data.method.annotation.Argument;
import org.springframework.graphql.data.method.annotation.MutationMapping;
import org.springframework.graphql.data.method.annotation.QueryMapping;
import org.springframework.security.access.annotation.Secured;
import org.springframework.stereotype.Controller;

import java.util.List;

@Controller
@RequiredArgsConstructor
class CategoryController {

    private final CategoryService categoryService;

    @Secured({"ROLE_ADMIN", "ROLE_SUPERADMIN"})
    @MutationMapping
    Category createCategory(@Argument final Category category) {
        return categoryService.createCategory(category);
    }

    @QueryMapping
    List<Category> findAllCategories() {
        return categoryService.findAllCategories();
    }

    @QueryMapping
    Category getCategoryByName(@Argument final ECategory categoryName) {
        return categoryService.getCategoryByName(categoryName);
    }
}
