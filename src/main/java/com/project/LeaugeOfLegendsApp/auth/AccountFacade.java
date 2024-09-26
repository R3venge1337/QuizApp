package com.project.LeaugeOfLegendsApp.auth;

import com.project.LeaugeOfLegendsApp.auth.dto.AccountResponse;
import com.project.LeaugeOfLegendsApp.auth.dto.FilterAccountForm;
import com.project.LeaugeOfLegendsApp.auth.dto.RoleUuidForm;
import com.project.LeaugeOfLegendsApp.auth.dto.UpdateAccountForm;
import com.project.LeaugeOfLegendsApp.shared.controller.PageDto;
import com.project.LeaugeOfLegendsApp.shared.controller.PageableRequest;

import java.util.UUID;

public interface AccountFacade {

    AccountResponse updateAccount(final UUID uuid, final UpdateAccountForm updateForm);

    PageDto<AccountResponse> getAllAccounts(final FilterAccountForm filterForm, final PageableRequest pageableRequest);

    AccountResponse findByUsername(final String username);

    AccountResponse findByUuid(final UUID uuid);

    void assignRolesToAccount(final UUID accountUuid, final RoleUuidForm uuids);

    void unassignRolesToAccount(final UUID accountUuid, final RoleUuidForm uuids);

    void deleteByUuid(final UUID uuid);
}
