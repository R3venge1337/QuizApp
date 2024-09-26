package com.project.LeaugeOfLegendsApp.auth.domain;

import com.project.LeaugeOfLegendsApp.auth.domain.QUser;
import com.project.LeaugeOfLegendsApp.auth.UserFacade;
import com.project.LeaugeOfLegendsApp.auth.dto.FilterUserForm;
import com.project.LeaugeOfLegendsApp.auth.dto.RoleUuidForm;
import com.project.LeaugeOfLegendsApp.auth.dto.UpdateUserForm;
import com.project.LeaugeOfLegendsApp.auth.dto.UserResponse;
import com.project.LeaugeOfLegendsApp.auth.dto.UserWithAccount;
import com.project.LeaugeOfLegendsApp.exceptions.NotFoundException;
import com.project.LeaugeOfLegendsApp.exceptions.UserNotFoundException;
import com.project.LeaugeOfLegendsApp.shared.controller.PageDto;
import com.project.LeaugeOfLegendsApp.shared.controller.PageableRequest;
import com.project.LeaugeOfLegendsApp.shared.controller.PageableUtils;
import com.querydsl.core.types.Predicate;
import lombok.RequiredArgsConstructor;
import org.apache.commons.lang3.Validate;
import org.springframework.data.domain.Page;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Set;
import java.util.UUID;
import java.util.stream.Collectors;

import static com.project.LeaugeOfLegendsApp.auth.domain.DomainMapper.mapToUserResponse;
import static com.project.LeaugeOfLegendsApp.auth.domain.UserService.ErrorMessages.USER_NOT_FOUND;

@Service
@RequiredArgsConstructor
class UserService implements UserFacade {

    private final UserRepository userRepository;

    private final AccountRepository accountRepository;

    private final RoleRepository roleRepository;

    static final class ErrorMessages {
        static final String USER_NOT_FOUND = "User has not been found: %s";
    }


    @Override
    @Transactional
    public UserResponse updateUser(final UUID uuid, final UpdateUserForm updateForm) {
        User user = userRepository.findByUuid(uuid)
                .orElseThrow(() -> new NotFoundException(USER_NOT_FOUND, uuid));

        user.setName(updateForm.name());
        user.setSecondName(updateForm.secondName());
        user.setSurname(updateForm.surname());
        user.setYearOfBirth(updateForm.yearOfBirth());

        return mapToUserResponse(user);
    }

    @Override
    public PageDto<UserResponse> getAllUsers(final FilterUserForm filterForm, final PageableRequest pageableRequest) {
        Predicate predicate = QUser.user.account.username.matches(filterForm.username());
        Page<User> all = userRepository.findAll(predicate, PageableUtils.createPageable(pageableRequest));
        return PageableUtils.toDto(all.map(DomainMapper::mapToUserResponse));
    }

    @Override
    public UserResponse findByUsername(final String username) {
        return mapToUserResponse(userRepository.findByUsername(username)
                .orElseThrow(() -> new NotFoundException(USER_NOT_FOUND, username)));
    }

    @Override
    public UserResponse findByUuid(final UUID uuid) {
        return mapToUserResponse(userRepository.findByUuid(uuid)
                .orElseThrow(() -> new NotFoundException(USER_NOT_FOUND, uuid)));
    }


    @Override
    @Transactional
    public void assignRolesToUser(final UUID userUuid, final RoleUuidForm uuidsForm) {
        Validate.notNull(uuidsForm, "RoleUuidForm cannot be null");
        User user = userRepository.findByUuid(userUuid)
                .orElseThrow(() -> new NotFoundException(USER_NOT_FOUND, userUuid));
        Set<Role> roles = roleRepository.findRolesByUuidIn(uuidsForm.uuids());
        user.getAccount().getRoles().addAll(roles);
    }

    @Override
    @Transactional
    public void unassignRolesToUser(final UUID userUuid, final RoleUuidForm uuidsForm) {
        Validate.notNull(uuidsForm, "RoleUuidForm cannot be null");
        User user = userRepository.findByUuid(userUuid)
                .orElseThrow(() -> new NotFoundException(USER_NOT_FOUND, userUuid));
        Set<Role> roles = roleRepository.findRolesByUuidIn(uuidsForm.uuids());
        user.getAccount().getRoles().addAll(roles);
    }

    @Override
    public void deleteByUuid(final UUID uuid) {
        userRepository.deleteByUuid(uuid);
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        final User user = userRepository.findByUsername(username)
                .orElseThrow(() -> new UserNotFoundException(String.format(USER_NOT_FOUND, username)));
        final Account account = user.getAccount();
        return new AuthorizationUser(new UserWithAccount(user.getUuid(), account.getUuid(),
                account.getUsername(),
                account.getPassword(),
                account.getEmail(),
                account.getRoles().stream().map(Role::getName).collect(Collectors.toSet()),
                account.getIsActive(),
                account.getIsLocked(),
                account.getIsTwoFactorAuthenticationEnabled())
        );
    }
}
