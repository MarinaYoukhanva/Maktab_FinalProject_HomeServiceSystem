package com.freshome.dto;

import com.freshome.entity.enumeration.UserStatus;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.experimental.FieldDefaults;

import java.time.LocalDateTime;

@AllArgsConstructor
@Getter
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public abstract class UserCreateDTO {

    @NotBlank(message = "firstname can not be null or blank! ")
    @Size(min = 2, max = 50, message = "too short or long firstname! ")
    @Pattern(regexp = "^[a-zA-Z]+$", message = "firstname only can contains letters! ")
    String firstname;

    @NotBlank(message = "lastname can not be null or blank! ")
    @Size(min = 2, max = 50, message = "too short or long lastname! ")
    @Pattern(regexp = "^[a-zA-Z]+$", message = "lastname only can contains letters! ")
    String lastname;

    @NotBlank(message = "email can not be null or blank! ")
    @Size(max = 150, message = "long email! ")
    @Pattern(regexp = "^[^@]+@[a-zA-Z0-9.-]+\\.[a-zA-Z]{2,}$", message = "invalid email format! ")
    String email;

    @NotBlank(message = "password can not be null or blank! ")
    @Pattern(regexp = "^(?=.*[A-Z])(?=.*[a-z])(?=.*\\d)[A-Za-z\\d@#$%&]{8,}$", message =
            "password has to contain at least one uppercase letter, one lowercase letter and one digit")
    String password;

    @NotNull(message = "registerDateTime can not be null! ")
    LocalDateTime registerDateTime;

    @NotNull(message = "status can not be null! ")
    UserStatus status;

    @Size(min = 13, max = 15, message = "short or long phone_number! ")
    @Pattern(regexp = "^((0098)0?9|\\+980?9)[01239]\\d{8}$", message = "invalid phone-number! ")
    String phoneNumber;
}
