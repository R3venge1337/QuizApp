package com.project.LeaugeOfLegendsApp.auth.dto;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

@RequiredArgsConstructor
@Getter
@Setter
public class JwtResponse {

    private String token;

    public JwtResponse(final String accessToken) {
        this.token = accessToken;
    }
}
