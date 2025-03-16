package com.freshome.entity;

import com.freshome.entity.embeddable.Address;
import com.freshome.entity.enumeration.OrderStatus;
import jakarta.persistence.Embedded;

import java.time.LocalDateTime;

public class Order {

    Long suggestedPriceByCustomer;
    String description;
    LocalDateTime orderPlacementDateTime;
    LocalDateTime orderExecutionDateTime;

    @Embedded
    Address address;

    OrderStatus status;
}
