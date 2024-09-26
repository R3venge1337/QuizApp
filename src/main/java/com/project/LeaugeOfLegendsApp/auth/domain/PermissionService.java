package com.project.LeaugeOfLegendsApp.auth.domain;

import com.project.LeaugeOfLegendsApp.auth.PermissionFacade;
import com.project.LeaugeOfLegendsApp.auth.domain.QPermission;
import com.project.LeaugeOfLegendsApp.auth.dto.CreatePermissionForm;
import com.project.LeaugeOfLegendsApp.auth.dto.FilterPermissionForm;
import com.project.LeaugeOfLegendsApp.auth.dto.PermissionResponse;
import com.project.LeaugeOfLegendsApp.auth.dto.UpdatePermissionForm;
import com.project.LeaugeOfLegendsApp.exceptions.AlreadyExistException;
import com.project.LeaugeOfLegendsApp.exceptions.NotFoundException;
import com.project.LeaugeOfLegendsApp.shared.controller.PageDto;
import com.project.LeaugeOfLegendsApp.shared.controller.PageableRequest;
import com.project.LeaugeOfLegendsApp.shared.controller.PageableUtils;
import com.querydsl.core.types.Predicate;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.UUID;

import static com.project.LeaugeOfLegendsApp.auth.domain.PermissionService.ErrorMessages.PERMISSION_NOT_FOUND;

@Service
@RequiredArgsConstructor
class PermissionService implements PermissionFacade {

    private final PermissionRepository permissionRepository;

    static final class ErrorMessages {
        static final String PERMISSION_NOT_FOUND = "Permission has not been found by uuid: %s";
    }

    @Override
    public PageDto<PermissionResponse> findAllPermissions(final FilterPermissionForm filterForm, final PageableRequest pageableRequest) {
        Predicate nameMatchPredicate = QPermission.permission.name.matches(filterForm.name());
        Page<Permission> page = permissionRepository.findAll(nameMatchPredicate, PageableUtils.createPageable(pageableRequest));
        return PageableUtils.toDto(page.map(this::mapToPermissionResponse));
    }

    @Override
    public PermissionResponse findByUuid(final UUID uuid) {
        return mapToPermissionResponse(permissionRepository.findByUuid(uuid)
                .orElseThrow(() -> new NotFoundException(PERMISSION_NOT_FOUND, uuid)));
    }

    @Override
    @Transactional
    public PermissionResponse createPermission(final CreatePermissionForm createForm) {
        if (permissionRepository.existsByName(createForm.name())) {
            throw new AlreadyExistException("This permission with name %s exist", createForm.name());
        }

        return mapToPermissionResponse(permissionRepository.insert(createPermissionFromCreateForm(createForm)));
    }

    @Override
    @Transactional
    public PermissionResponse updatePermission(final UUID uuid, final UpdatePermissionForm updateForm) {
        Permission permission = permissionRepository.findByUuid(uuid).
                orElseThrow(() -> new NotFoundException(PERMISSION_NOT_FOUND, uuid));

        permission.setName(updateForm.name());

        return mapToPermissionResponse(permission);
    }

    @Override
    @Transactional
    public void deleteByUuid(final UUID uuid) {
        permissionRepository.deleteByUuid(uuid);
    }

    private Permission createPermissionFromCreateForm(final CreatePermissionForm createForm) {
        return new Permission(createForm.name());
    }

    PermissionResponse mapToPermissionResponse(final Permission permission) {
        return new PermissionResponse(permission.getUuid(), permission.getName());
    }
}
