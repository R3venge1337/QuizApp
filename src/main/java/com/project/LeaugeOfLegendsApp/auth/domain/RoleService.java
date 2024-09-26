package com.project.LeaugeOfLegendsApp.auth.domain;

import com.project.LeaugeOfLegendsApp.auth.dto.PermissionResponse;
import com.project.LeaugeOfLegendsApp.auth.RoleFacade;
import com.project.LeaugeOfLegendsApp.auth.dto.RoleResponse;
import com.project.LeaugeOfLegendsApp.auth.dto.CreateRoleForm;
import com.project.LeaugeOfLegendsApp.auth.dto.FilterRoleForm;
import com.project.LeaugeOfLegendsApp.auth.dto.UpdateRoleForm;
import com.project.LeaugeOfLegendsApp.exceptions.AlreadyExistException;
import com.project.LeaugeOfLegendsApp.exceptions.NotFoundException;
import com.project.LeaugeOfLegendsApp.shared.controller.PageDto;
import com.project.LeaugeOfLegendsApp.shared.controller.PageableRequest;
import com.project.LeaugeOfLegendsApp.shared.controller.PageableUtils;
import com.project.LeaugeOfLegendsApp.shared.predicate.RolePredicateFactory;
import com.querydsl.core.types.Predicate;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.UUID;
import java.util.stream.Collectors;

import static com.project.LeaugeOfLegendsApp.auth.domain.RoleService.ErrorMessages.ROLE_NOT_FOUND;

@Service
@RequiredArgsConstructor
class RoleService implements RoleFacade {

    private final String ROLE_PREFIX = "ROLE_";
    private final RoleRepository roleRepository;

    static final class ErrorMessages {
        static final String ROLE_NOT_FOUND = "Role has not been found by uuid: %s";
    }

    @Override
    public RoleResponse findByName(final String name) {
        return mapToRoleResponse(roleRepository.findByName(name)
                .orElseThrow(() -> new NotFoundException(ROLE_NOT_FOUND, name)));
    }

    @Override
    public PageDto<RoleResponse> getAllRoles(final FilterRoleForm filterForm, final PageableRequest pageableRequest) {
        //Predicate predicate = QRole.role.name.matches(filterForm.name());

        Predicate predicate = new RolePredicateFactory(filterForm).toPredicate();
        Page<Role> pageRoles = roleRepository.findAll(predicate, PageableUtils.createPageable(pageableRequest));
        return PageableUtils.toDto(pageRoles.map(this::mapToRoleResponse));
    }

    @Override
    @Transactional
    public RoleResponse createRole(final CreateRoleForm createForm) {
        if (roleRepository.existsByName(createFullRole(createForm.name()))) {
            throw new AlreadyExistException("Role with name %s already exist", createFullRole(createForm.name()));
        }

        return mapToRoleResponse(roleRepository.insert(mapToRole(createForm)));
    }

    @Override
    @Transactional
    public RoleResponse updateRole(final UUID uuid, final UpdateRoleForm updateForm) {
        Role role = roleRepository.findByUuid(uuid)
                .orElseThrow(() -> new NotFoundException(ROLE_NOT_FOUND, uuid));

        role.setName(ROLE_PREFIX + updateForm.name());

        return mapToRoleResponse(role);
    }

    @Override
    @Transactional
    public void deleteRole(final UUID uuid) {
        roleRepository.findByUuid(uuid)
                .orElseThrow(() -> new NotFoundException(ROLE_NOT_FOUND, uuid));
    }

    private Role mapToRole(final CreateRoleForm role) {
        return new Role(createFullRole(role.name()), new HashSet<>());
    }

    private String createFullRole(final String role) {
        return ROLE_PREFIX + role;
    }

    private RoleResponse mapToRoleResponse(final Role role) {
        return new RoleResponse(role.getUuid(), role.getName(), mapToPermissionResponse(role));
    }

    private Set<PermissionResponse> mapToPermissionResponse(final Role role) {
        return role.getPermissions().stream().map(this::createPermissionResponse).collect(Collectors.toUnmodifiableSet());
    }

    private PermissionResponse createPermissionResponse(final Permission permission) {
        return new PermissionResponse(permission.getUuid(), permission.getName());
    }
}
