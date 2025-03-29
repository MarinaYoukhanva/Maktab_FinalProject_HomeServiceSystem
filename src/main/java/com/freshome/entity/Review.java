package com.freshome.entity;

import com.freshome.entity.base.BaseEntity;
import jakarta.persistence.*;
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
@Table(name = "review")
public class Review extends BaseEntity<Long> {

    @Column(nullable = false)
    Double rating;

    String comment;

    @ManyToOne
    Customer customer;

    @OneToOne(optional = false)
    Order order;

    @ManyToOne(optional = false)
    Expert expert;
}
