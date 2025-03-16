package com.freshome.entity;

import com.freshome.entity.base.BaseEntity;
import com.freshome.entity.embeddable.Address;
import com.freshome.entity.enumeration.OrderStatus;
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
public class Order extends BaseEntity<Long> {

    Long suggestedPriceByCustomer;
    String description;
    LocalDateTime orderPlacementDateTime;
    LocalDateTime orderExecutionDateTime;

    @Embedded
    Address address;

    @Enumerated(EnumType.STRING)
    OrderStatus status;

    @ManyToOne
    Customer customer;

    @ManyToOne
    Expert expert;

    @ManyToOne
    SubService subService;

    @OneToMany(mappedBy = "order")
    List<Offer> offers;
}
