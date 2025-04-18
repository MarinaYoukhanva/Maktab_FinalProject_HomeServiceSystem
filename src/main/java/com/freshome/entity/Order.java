package com.freshome.entity;

import com.freshome.entity.base.BaseEntity;
import com.freshome.entity.embeddable.Address;
import com.freshome.entity.enumeration.OrderStatus;
import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.FieldDefaults;
import lombok.experimental.SuperBuilder;
import org.hibernate.annotations.SoftDelete;

import java.time.LocalDateTime;
import java.util.List;

@Getter
@Setter
@SuperBuilder
@AllArgsConstructor
@NoArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
@Entity
@SoftDelete
public class Order extends BaseEntity<Long> {

    Long suggestedPriceByCustomer;

    @Column(nullable = false)
    String description;

    @Column(nullable = false, updatable = false)
    LocalDateTime orderPlacementDateTime;

    LocalDateTime orderExecutionDateTime;

    @Embedded
    Address address;

    @Column(nullable = false)
    @Enumerated(EnumType.STRING)
    OrderStatus status;

    @ManyToOne
//            (optional = false)
    Customer customer;

    @ManyToOne
    Expert expert;

    @ManyToOne
    SubService subService;

    @OneToMany(mappedBy = "order")
    List<Offer> offers;
}
