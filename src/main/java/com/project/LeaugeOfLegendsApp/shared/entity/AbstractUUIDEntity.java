package com.project.LeaugeOfLegendsApp.shared.entity;


import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import lombok.experimental.FieldNameConstants;

import java.util.UUID;

@Getter
@FieldNameConstants
@EqualsAndHashCode(onlyExplicitlyIncluded = true, callSuper = false)
public abstract class AbstractUUIDEntity extends AbstractEntity {

    @EqualsAndHashCode.Include
    @Setter
    protected UUID uuid = UUID.randomUUID();

    @Override
    public String toString() {
        return this.getClass().getSimpleName() + '(' + "uuid=" + uuid + ')';
    }
}