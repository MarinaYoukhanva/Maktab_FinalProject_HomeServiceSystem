package com.freshome.entity.dto.customer;

import com.freshome.entity.dto.UserCreateDTO;
import com.freshome.entity.enumeration.UserStatus;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import lombok.Getter;

import java.time.LocalDateTime;

@Getter
public class CustomerUpdateDTO extends UserCreateDTO {

    @NotNull(message = "id can not be null for updating")
    @Positive
    Long id;

    public CustomerUpdateDTO(
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
