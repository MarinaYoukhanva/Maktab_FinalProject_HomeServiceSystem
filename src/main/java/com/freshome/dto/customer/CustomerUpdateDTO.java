package com.freshome.dto.customer;

import com.freshome.dto.UserUpdateDTO;
import com.freshome.entity.enumeration.UserStatus;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

@Getter
@SuperBuilder
@NoArgsConstructor
public class CustomerUpdateDTO extends UserUpdateDTO {

    @NotNull(message = "id can not be null for updating! ")
    @Positive
    Long id;

    public CustomerUpdateDTO(
            Long id,
            String firstname,
            String lastname,
            String email,
            UserStatus status,
            String phoneNumber) {
        super(firstname, lastname, email, status, phoneNumber);
        this.id = id;
    }
}
