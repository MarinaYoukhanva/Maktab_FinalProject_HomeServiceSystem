package com.freshome.entity;

import com.freshome.entity.base.BaseEntity;
import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.FieldDefaults;
import lombok.experimental.SuperBuilder;

import java.time.LocalDateTime;
import java.util.List;

@Getter
@Setter
@SuperBuilder
@AllArgsConstructor
@NoArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
@Entity
@Table(uniqueConstraints = @UniqueConstraint(
                columnNames = {"order_id", "expert_id"}))
public class Offer extends BaseEntity<Long> {

    @Column(nullable = false, updatable = false)
    LocalDateTime offerRegisterDateTime;

    @Column(nullable = false)
    Long suggestedPriceByExpert;

    @Column(nullable = false)
    Integer durationInHours;

    LocalDateTime startDateTime;

    @ManyToOne(optional = false)
    Order order;

    @ManyToOne(optional = false)
    Expert expert;
}
