package com.freshome.entity;

import com.freshome.entity.base.BaseEntity;
import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.experimental.FieldDefaults;
import lombok.experimental.SuperBuilder;

import java.time.LocalDateTime;
import java.util.List;

@Getter
@SuperBuilder
@AllArgsConstructor
@NoArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
@Entity
@Table(uniqueConstraints = @UniqueConstraint(
                columnNames = {"order_id", "expert_id"}))
public class Offer extends BaseEntity<Long> {

    LocalDateTime offerRegisterDateTime;
    Long suggestedPriceByExpert;
    Integer durationInHours;
    LocalDateTime startDateTime;

    @ManyToOne
    Order order;

    @ManyToOne
    Expert expert;
}
