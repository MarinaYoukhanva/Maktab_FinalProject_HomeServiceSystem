package com.freshome.entity;

import com.freshome.entity.base.BaseEntity;
import com.freshome.entity.enumeration.UserStatus;
import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.experimental.FieldDefaults;
import lombok.experimental.SuperBuilder;

import java.time.LocalDateTime;

@Getter
@SuperBuilder
@AllArgsConstructor
@NoArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
@Entity
@Inheritance(strategy = InheritanceType.JOINED)
public abstract class User extends BaseEntity<Long> {

    String firstname;
    String lastname;
    String email;
    String password;
    LocalDateTime registerDateTime;

    @Enumerated(EnumType.STRING)
    UserStatus status;

//    String username;
    String phoneNumber;

}
