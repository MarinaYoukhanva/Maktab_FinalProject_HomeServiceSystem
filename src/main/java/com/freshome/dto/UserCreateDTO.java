package com.freshome.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.experimental.FieldDefaults;

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

    @NotBlank(message = "username can not be null or blank! ")
    @Size(min = 2, max = 50, message = "username has to contain 8-50 characters! ")
    String username;

    @NotBlank(message = "password can not be null or blank! ")
    @Pattern(regexp = "^(?=.*[A-Z])(?=.*[a-z])(?=.*\\d)(?=.*[@#$%&])[A-Za-z\\d@#$%&]{8,}$", message =
            """
                    password must be at least 8 characters long
                    and include at least one uppercase letter, one lowercase letter, one digit,
                    and one special character (@, #, $, %, &).
                    """)
    String password;

    @NotBlank(message = "email can not be null or blank! ")
    @Size(max = 150, message = "long email! ")
    @Pattern(regexp = "^[^@]+@[a-zA-Z0-9.-]+\\.[a-zA-Z]{2,}$", message = "invalid email format! ")
    String email;

}
