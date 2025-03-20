package com.freshome.entity;

import com.freshome.entity.base.BaseEntity;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.OneToMany;
import lombok.*;
import lombok.experimental.FieldDefaults;
import lombok.experimental.SuperBuilder;

import java.util.List;

@Getter
@Setter
@SuperBuilder
@AllArgsConstructor
@NoArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
@Entity
public class ServiceCategory extends BaseEntity<Long> {

    @Column(nullable = false, unique = true)
    String name;

    @Column(nullable = false)
    String description;

    @OneToMany(mappedBy = "category")
    List<SubService> services;
}
