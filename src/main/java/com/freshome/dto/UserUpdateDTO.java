package com.freshome.dto;

import com.freshome.entity.enumeration.UserStatus;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.experimental.FieldDefaults;
import lombok.experimental.SuperBuilder;

@AllArgsConstructor
@Getter
@SuperBuilder
@NoArgsConstructor(force = true)
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public abstract class UserUpdateDTO {

    @Size(min = 2, max = 50, message = "too short or long firstname! ")
    @Pattern(regexp = "^[a-zA-Z]+$", message = "firstname only can contains letters! ")
    String firstname;

    @Size(min = 2, max = 50, message = "too short or long lastname! ")
    @Pattern(regexp = "^[a-zA-Z]+$", message = "lastname only can contains letters! ")
    String lastname;

    @Size(max = 150, message = "long email! ")
    @Pattern(regexp = "^[^@]+@[a-zA-Z0-9.-]+\\.[a-zA-Z]{2,}$", message = "invalid email format! ")
    String email;

    UserStatus status;

    @Size(min = 13, max = 15, message = "short or long phone_number! ")
    @Pattern(regexp = "^((0098)0?9|\\+980?9)[01239]\\d{8}$", message = "invalid phone-number! ")
    String phoneNumber;

    //    String password;
}
