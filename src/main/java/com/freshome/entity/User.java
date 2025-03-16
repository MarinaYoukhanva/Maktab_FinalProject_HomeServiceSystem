package com.freshome.entity;

import com.freshome.entity.enumeration.UserStatus;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

import java.time.LocalDateTime;

//@AllArgsConstructor
//@NoArgsConstructor
@SuperBuilder
@AllArgsConstructor
@NoArgsConstructor
public class User {

    String firstname;
    String lastname;
    String email;
    String password;
    UserStatus status;
    LocalDateTime registerDateTime;

    String phoneNumber;
}
