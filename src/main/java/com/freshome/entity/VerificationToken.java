package com.freshome.entity;

import com.freshome.entity.base.BaseEntity;
import jakarta.persistence.Entity;
import jakarta.persistence.ManyToOne;
import lombok.Getter;
import lombok.Setter;

@Entity
@Setter
@Getter
public class VerificationToken extends BaseEntity<Long> {

    private String token;

    @ManyToOne
    private User user;

    private boolean used = false;
}