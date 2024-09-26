package com.project.LeaugeOfLegendsApp.auth.domain;

import com.project.LeaugeOfLegendsApp.auth.AccountFacade;
import com.project.LeaugeOfLegendsApp.auth.PasswordFacade;
import com.project.LeaugeOfLegendsApp.auth.dto.AccountResponse;
import com.project.LeaugeOfLegendsApp.auth.dto.FilterAccountForm;
import com.project.LeaugeOfLegendsApp.auth.dto.PermissionResponse;
import com.project.LeaugeOfLegendsApp.auth.dto.RoleResponse;
import com.project.LeaugeOfLegendsApp.auth.dto.RoleUuidForm;
import com.project.LeaugeOfLegendsApp.auth.dto.UpdateAccountForm;
import com.project.LeaugeOfLegendsApp.exceptions.NotFoundException;
import com.project.LeaugeOfLegendsApp.shared.controller.PageDto;
import com.project.LeaugeOfLegendsApp.shared.controller.PageableRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.UUID;
import java.util.stream.Collectors;

import static com.project.LeaugeOfLegendsApp.auth.domain.AccountService.ErrorMessages.ACCOUNT_NOT_FOUND;

@Service
@RequiredArgsConstructor
class AccountService implements AccountFacade {

    private final AccountRepository accountRepository;
    private final PasswordFacade passwordFacade;

    static final class ErrorMessages {
        static final String ACCOUNT_NOT_FOUND = "Account has not been found: %s";
    }

    @Override
    public PageDto<AccountResponse> getAllAccounts(final FilterAccountForm filterForm, final PageableRequest pageableRequest) {
        return null;
    }

    @Override
    public AccountResponse findByUsername(final String username) {
        return mapToAccountResponse(accountRepository.findByUsername(username)
                .orElseThrow(() -> new NotFoundException(ACCOUNT_NOT_FOUND, username)));
    }

    @Override
    public AccountResponse findByUuid(final UUID uuid) {
        return mapToAccountResponse(accountRepository.findByUuid(uuid)
                .orElseThrow(() -> new NotFoundException(ACCOUNT_NOT_FOUND, uuid)));
    }

    @Override
    @Transactional
    public AccountResponse updateAccount(final UUID uuid, final UpdateAccountForm updateForm) {
        Account account = accountRepository.findByUuid(uuid)
                .orElseThrow(() -> new NotFoundException(ACCOUNT_NOT_FOUND, uuid));

        account.setUsername(updateForm.username());
        account.setEmail(updateForm.email());
        account.setPassword(passwordFacade.encodePassword(updateForm.password()));

        return mapToAccountResponse(account);
    }

    @Override
    public void assignRolesToAccount(final UUID accountUuid, final RoleUuidForm uuids) {

    }

    @Override
    public void unassignRolesToAccount(final UUID accountUuid, final RoleUuidForm uuids) {

    }

    @Override
    public void deleteByUuid(final UUID uuid) {
        accountRepository.deleteByUuid(uuid);
    }

    private AccountResponse mapToAccountResponse(final Account account) {
        return new AccountResponse(account.getUuid(), account.getUsername(), account.getEmail(), account.getRoles().stream().map(this::mapToRoleResponse).collect(Collectors.toUnmodifiableSet()));
    }

    private RoleResponse mapToRoleResponse(final Role role) {
        return new RoleResponse(role.getUuid(), role.getName(), role.getPermissions().stream().map(this::mapToPermissionResponse).collect(Collectors.toUnmodifiableSet()));
    }

    private PermissionResponse mapToPermissionResponse(final Permission permission) {
        return new PermissionResponse(permission.getUuid(), permission.getName());
    }
}
