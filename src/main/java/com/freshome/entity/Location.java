package com.freshome.entity;

import com.freshome.entity.base.BaseEntity;
import jakarta.persistence.Entity;
import jakarta.persistence.OneToOne;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Location extends BaseEntity<Long> {

    private Double latitude;
    private Double longitude;

    @OneToOne
    private Order order;
}
