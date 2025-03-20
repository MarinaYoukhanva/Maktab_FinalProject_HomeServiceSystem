package com.freshome.dto;

import com.freshome.entity.enumeration.UserStatus;
import jakarta.validation.constraints.Size;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.experimental.FieldDefaults;
import lombok.experimental.SuperBuilder;

@AllArgsConstructor
@Getter
@SuperBuilder
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public abstract class UserUpdateDTO {

    @Size(min = 2)
    String firstname;

    @Size(min = 2)
    String lastname;
    String email;
//    String password;
    UserStatus status;
    String phoneNumber;
}
