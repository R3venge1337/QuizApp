package com.project.LeaugeOfLegendsApp.auth.domain;

import com.project.LeaugeOfLegendsApp.auth.AccountFacade;
import com.project.LeaugeOfLegendsApp.auth.PasswordFacade;
import com.project.LeaugeOfLegendsApp.auth.UserFacade;
import com.project.LeaugeOfLegendsApp.auth.dto.UserWithAccount;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

@Configuration
class AuthBeanConfiguration {

    @Bean
    AccountFacade accountFacade(final AccountRepository accountRepository, final PasswordFacade passwordFacade) {
        return new AccountService(accountRepository, passwordFacade);
    }

    @Bean
    UserDetailsService userDetailsService(final UserFacade userFacade) {
        return username -> {
            final UserWithAccount userWithAccount = userFacade.findByUsername(username);
            return new AuthorizationUser(userWithAccount);
        };
    }

    @Bean
    PasswordFacade passwordFacade(final PasswordEncoder passwordEncoder) {
        return new PasswordService(passwordEncoder);
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Bean
    public AuthenticationProvider authenticationProvider(final UserFacade userFacade) {
        DaoAuthenticationProvider authenticationProvider = new DaoAuthenticationProvider();

        authenticationProvider.setUserDetailsService(userDetailsService(userFacade));
        authenticationProvider.setPasswordEncoder(passwordEncoder());

        return authenticationProvider;
    }

    @Bean
    AuthenticationManager authenticationManager(final org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration authenticationConfiguration) throws Exception {
        return authenticationConfiguration.getAuthenticationManager();
    }
}
