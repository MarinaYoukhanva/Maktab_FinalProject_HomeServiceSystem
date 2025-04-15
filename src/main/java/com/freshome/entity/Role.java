package com.freshome.entity;

import com.freshome.entity.base.BaseEntity;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
public class Role extends BaseEntity<Long> {

    public static final String CUSTOMER = "ROLE_CUSTOMER";
    public static final String EXPERT = "ROLE_EXPERT";
    public static final String ADMIN = "ROLE_ADMIN";

    @Column(unique = true, nullable = false)
    private String name;
}
