package com.project.LeaugeOfLegendsApp.auth;

import jakarta.mail.MessagingException;

public interface EmailFacade {

    public void sendVerificationEmail(final String to, final String subject, final String text) throws MessagingException;
}
