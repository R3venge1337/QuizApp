package com.project.LeaugeOfLegendsApp.language;

import lombok.RequiredArgsConstructor;
import org.springframework.graphql.data.method.annotation.Argument;
import org.springframework.graphql.data.method.annotation.MutationMapping;
import org.springframework.graphql.data.method.annotation.QueryMapping;
import org.springframework.security.access.annotation.Secured;
import org.springframework.stereotype.Controller;

import java.util.List;

@Controller
@RequiredArgsConstructor
class LanguageController {
    private final LanguageService languageService;

    @Secured({"ROLE_ADMIN", "ROLE_SUPERADMIN"})
    @MutationMapping
    Language createLanguage(@Argument final Language language) {
        return languageService.createLanguage(language);
    }

    @QueryMapping
    List<Language> findAllLanguages() {
        return languageService.findAllLanguages();
    }

    @QueryMapping
    Language findLanguageByName(@Argument(name = "languageName") final ELanguage lang) {
        return languageService.findLanguageByName(lang);
    }
}
