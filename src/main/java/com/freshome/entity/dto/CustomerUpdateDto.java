package com.freshome.entity.dto;

import com.freshome.entity.enumeration.UserStatus;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.PositiveOrZero;
import lombok.Getter;

import java.time.LocalDateTime;

@Getter
public class CustomerUpdateDto extends UserCreateDTO{

    @NotNull(message = "id can not be null for updating")
    @PositiveOrZero
    Long id;

    public CustomerUpdateDto(
            Long id,
            String firstname,
            String lastname,
            String email,
            String password,
            LocalDateTime registerDateTime,
            UserStatus status,
            String phoneNumber) {
        super(firstname, lastname, email, password, registerDateTime, status, phoneNumber);
        this.id = id;
    }
}
