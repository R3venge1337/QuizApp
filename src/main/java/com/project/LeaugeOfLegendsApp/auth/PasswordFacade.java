package com.project.LeaugeOfLegendsApp.auth;

public interface PasswordFacade {

    String encodePassword(final String password);

    Boolean matchPassword(final String raw, final String encoded);
}
