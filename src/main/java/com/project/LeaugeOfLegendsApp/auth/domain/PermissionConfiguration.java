package com.project.LeaugeOfLegendsApp.auth.domain;

import com.project.LeaugeOfLegendsApp.auth.PermissionFacade;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
class PermissionConfiguration {

    @Bean
    PermissionFacade permissionFacade(final PermissionRepository permissionRepository) {
        return new PermissionService(permissionRepository);
    }
}
