package com.project.LeaugeOfLegendsApp.auth.domain;

import com.project.LeaugeOfLegendsApp.auth.UserFacade;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
class UserConfiguration {

    @Bean
    UserFacade userFacade(final UserRepository userRepository, final AccountRepository accountRepository, final RoleRepository roleRepository) {
        return new UserService(userRepository, accountRepository, roleRepository);
    }
}
