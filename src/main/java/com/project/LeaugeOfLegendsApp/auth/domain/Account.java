package com.project.LeaugeOfLegendsApp.auth.domain;

import com.project.LeaugeOfLegendsApp.shared.entity.AbstractUUIDEntity;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.Set;

@Document(collection = "accounts")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode(onlyExplicitlyIncluded = true, callSuper = true)
public class Account extends AbstractUUIDEntity {

    private String username;

    private String email;

    private String password;

    private Boolean isActive;

    private Boolean isLocked;

    private String LastLoginIp;

    private LocalDateTime createdAt;

    private LocalDateTime updatedAt;

    private LocalDateTime LoggedIn;

    private LocalDateTime LoggedOff;

    private LocalDateTime lastFailedAttempt;

    private int failedAttempts;

    private String passwordResetToken;

    private LocalDateTime passwordResetTokenExpiry;

    private Boolean isTwoFactorAuthenticationEnabled;

    @DBRef(lazy = true)
    @Setter(AccessLevel.NONE)
    private Set<Role> roles = new HashSet<>();

    public Account(final String username, final String email, final String password, final Boolean isActive, final Boolean isLocked) {
        this.username = username;
        this.email = email;
        this.password = password;
        this.isActive = isActive;
        this.isLocked = isLocked;
    }
}
