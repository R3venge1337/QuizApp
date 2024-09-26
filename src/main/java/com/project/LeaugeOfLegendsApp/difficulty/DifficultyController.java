package com.project.LeaugeOfLegendsApp.difficulty;

import lombok.RequiredArgsConstructor;
import org.springframework.graphql.data.method.annotation.Argument;
import org.springframework.graphql.data.method.annotation.MutationMapping;
import org.springframework.graphql.data.method.annotation.QueryMapping;
import org.springframework.security.access.annotation.Secured;
import org.springframework.stereotype.Controller;

import java.util.List;

@Controller
@RequiredArgsConstructor
class DifficultyController {
    private final DifficultyService difficultyService;

    @Secured({"ROLE_ADMIN", "ROLE_SUPERADMIN"})
    @MutationMapping
    Difficulty createDifficulty(@Argument final Difficulty difficulty) {
        return difficultyService.createDifficulty(difficulty);
    }

    @QueryMapping
    List<Difficulty> findAllDifficulties() {
        return difficultyService.findAllDifficulties();
    }

    @QueryMapping
    Difficulty getDifficultyByName(@Argument final EDifficulty difficultyName) {
        return difficultyService.findDifficultyByName(difficultyName);
    }
}
