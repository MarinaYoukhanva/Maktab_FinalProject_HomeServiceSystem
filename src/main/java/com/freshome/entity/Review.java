package com.freshome.entity;

import com.freshome.entity.base.BaseEntity;
import jakarta.persistence.Entity;
import jakarta.persistence.ManyToOne;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.experimental.FieldDefaults;
import lombok.experimental.SuperBuilder;

@Getter
@SuperBuilder
@AllArgsConstructor
@NoArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
@Entity
public class Review extends BaseEntity<Long> {

    Integer rating;
    String comment;

    @ManyToOne
    Customer customer;

    @ManyToOne
    Order order;

    @ManyToOne
    Expert expert;
}
