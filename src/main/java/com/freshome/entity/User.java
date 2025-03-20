package com.freshome.entity;

import com.freshome.entity.base.BaseEntity;
import com.freshome.entity.enumeration.UserStatus;
import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.FieldDefaults;
import lombok.experimental.SuperBuilder;

import java.time.LocalDateTime;

@Getter
@Setter
@SuperBuilder
@AllArgsConstructor
@NoArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
@Entity
@Inheritance(strategy = InheritanceType.JOINED)
public abstract class User extends BaseEntity<Long> {

    @Column(nullable = false, length = 50)
    String firstname;

    @Column(nullable = false, length = 50)
    String lastname;

    @Column(nullable = false, unique = true, length = 150)
    String email;

    @Column(nullable = false)
    String password;

    @Column(nullable = false, updatable = false)
    LocalDateTime registerDateTime;

    @Column(nullable = false)
    @Enumerated(EnumType.STRING)
    UserStatus status;

    @Column(unique = true, length = 15)
    String phoneNumber;

    //    String username;
}
