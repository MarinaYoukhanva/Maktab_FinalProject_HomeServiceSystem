package com.freshome.entity;

import com.freshome.entity.base.BaseEntity;
import com.freshome.entity.enumeration.UserStatus;
import jakarta.persistence.*;
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
public class Customer extends BaseEntity<Long> {

    @Column(unique = true, length = 15)
    String phoneNumber;

    @Column(nullable = false)
    @Enumerated(EnumType.STRING)
    UserStatus status;

    @OneToOne(optional = false, fetch = FetchType.EAGER)
    User user;

    @OneToOne(optional = false, orphanRemoval = true)
    Credit credit;
}
