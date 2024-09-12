package com.project.LeaugeOfLegendsApp.shared.entity;


import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.experimental.FieldNameConstants;
import org.springframework.data.annotation.Id;

@Getter
@NoArgsConstructor
@FieldNameConstants
public abstract class AbstractEntity {

    @Id
    protected String id;
}