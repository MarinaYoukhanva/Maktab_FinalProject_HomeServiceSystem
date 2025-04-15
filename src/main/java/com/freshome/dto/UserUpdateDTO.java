package com.freshome.dto;

import com.freshome.entity.enumeration.UserStatus;
import jakarta.validation.constraints.NotBlank;
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

    @NotBlank(message = "username can not be null or blank! ")
    @Size(min = 2, max = 50, message = "username has to contain 8-50 characters! ")
    String username;

    @Size(max = 150, message = "long email! ")
    @Pattern(regexp = "^[^@]+@[a-zA-Z0-9.-]+\\.[a-zA-Z]{2,}$", message = "invalid email format! ")
    String email;

    //    String password;
}
