package com.project.LeaugeOfLegendsApp.auth.security;

import com.project.LeaugeOfLegendsApp.auth.dto.UserWithAccount;
import io.jsonwebtoken.Claims;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;
import java.util.List;
import java.util.Set;
import java.util.UUID;

@Component
@RequiredArgsConstructor
public class JwtRequestFilter extends OncePerRequestFilter {

    private final JwtTokenUtil jwtTokenUtil;


    @Override
    protected void doFilterInternal(final HttpServletRequest request, final HttpServletResponse response, final FilterChain filterChain)
            throws ServletException, IOException {
        final String authHeader = request.getHeader("Authorization");
        final String jwt;
        final String login;

        if (authHeader == null || !authHeader.startsWith("Bearer ")) {
            filterChain.doFilter(request, response);
            return;
        }

        jwt = authHeader.substring(7);

        login = jwtTokenUtil.getUsernameFromToken(jwt);
        if (login != null && SecurityContextHolder.getContext().getAuthentication() == null) {
            final Claims claims = jwtTokenUtil.getAllClaimsFromToken(jwt);
            final String username = claims.get(JwtTokenUtil.USER_LOGIN, String.class);
            final String userRole = claims.get(JwtTokenUtil.USER_TYPE, String.class);
            final String accountUuid = claims.get(JwtTokenUtil.ACCOUNT_UUID, String.class);
            final String userUuid = claims.get(JwtTokenUtil.USER_UUID, String.class);
            final String accountPassword = claims.get(JwtTokenUtil.USER_PASSWORD, String.class);
            final String userEmail = claims.get(JwtTokenUtil.USER_EMAIL, String.class);
            final String accountEnabled = claims.get(JwtTokenUtil.ACCOUNT_ENABLED, String.class);
            final String accountLocked = claims.get(JwtTokenUtil.ACCOUNT_LOCKED, String.class);


            if (!jwtTokenUtil.isTokenExpired(jwt)) {
                final UsernamePasswordAuthenticationToken authenticationToken =
                        new UsernamePasswordAuthenticationToken(new UserWithAccount(UUID.fromString(userUuid), UUID.fromString(accountUuid), username, accountPassword, userEmail, Set.of(userRole), Boolean.valueOf(accountEnabled), Boolean.valueOf(accountLocked)), null, List.of(new SimpleGrantedAuthority(userRole)));

                authenticationToken.setDetails(
                        new WebAuthenticationDetailsSource().buildDetails(request)
                );

                SecurityContextHolder.getContext().setAuthentication(authenticationToken);
            }
        }

        filterChain.doFilter(request, response);
    }
}
