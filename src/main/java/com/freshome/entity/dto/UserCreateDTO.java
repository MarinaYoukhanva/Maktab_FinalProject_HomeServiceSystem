package com.freshome.entity.dto;

import com.freshome.entity.enumeration.UserStatus;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.experimental.FieldDefaults;

import java.time.LocalDateTime;

@AllArgsConstructor
@Getter
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public abstract class UserCreateDTO {

    String firstname;
    String lastname;
    String email;
    String password;
    LocalDateTime registerDateTime;
    UserStatus status;
    String phoneNumber;
}
