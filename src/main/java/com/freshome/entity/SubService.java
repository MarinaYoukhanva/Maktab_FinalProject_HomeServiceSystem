package com.freshome.entity;

import com.freshome.entity.base.BaseEntity;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.ManyToOne;
import lombok.*;
import lombok.experimental.FieldDefaults;
import lombok.experimental.SuperBuilder;
import org.hibernate.annotations.SoftDelete;

@Getter
@Setter
@SuperBuilder
@AllArgsConstructor
@NoArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
@Entity
@SoftDelete
public class SubService extends BaseEntity<Long> {

    @Column(nullable = false, unique = true)
    String name;

    @Column(nullable = false)
    Long basePrice;

    @Column(nullable = false)
    String description;

    @ManyToOne(optional = false)
    ServiceCategory category;
}
