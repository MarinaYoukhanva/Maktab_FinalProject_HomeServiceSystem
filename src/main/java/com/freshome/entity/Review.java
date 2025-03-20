package com.freshome.entity;

import com.freshome.entity.base.BaseEntity;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.*;
import lombok.experimental.FieldDefaults;
import lombok.experimental.SuperBuilder;

@Getter
@Setter
@SuperBuilder
@AllArgsConstructor
@NoArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
@Entity
public class Review extends BaseEntity<Long> {

    @Column(nullable = false)
    Integer rating;

    String comment;

    @ManyToOne
    Customer customer;

    @ManyToOne(optional = false)
    Order order;

    @ManyToOne(optional = false)
    Expert expert;
}
