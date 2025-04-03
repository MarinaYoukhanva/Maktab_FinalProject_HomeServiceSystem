package com.freshome.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import lombok.Getter;

public record ChangePasswordDTO(
        @NotBlank(message = "Old password cannot be null or blank!")
        String oldPassword,

        @NotBlank(message = "password can not be null or blank! ")
        @Pattern(regexp = "^(?=.*[A-Z])(?=.*[a-z])(?=.*\\d)(?=.*[@#$%&])[A-Za-z\\d@#$%&]{8,}$", message =
                """
                        password must be at least 8 characters long
                        and include at least one uppercase letter, one lowercase letter, one digit,
                        and one special character (@, #, $, %, &).
                        """)
        String newPassword
) {
}
