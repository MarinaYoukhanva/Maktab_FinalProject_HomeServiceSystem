package com.freshome.dto;

import com.freshome.entity.enumeration.UserStatus;
import jakarta.validation.constraints.NotBlank;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.experimental.FieldDefaults;

import java.time.LocalDateTime;

@AllArgsConstructor
@Getter
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public abstract class UserCreateDTO {

    @NotBlank(message = "firstname can not be blank")
    String firstname;
    String lastname;
    String email;
    String password;
    LocalDateTime registerDateTime;
    UserStatus status;
    String phoneNumber;
}
