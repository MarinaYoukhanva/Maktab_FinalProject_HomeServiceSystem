package com.freshome.dto.customer;

import com.freshome.dto.UserUpdateDTO;
import com.freshome.entity.enumeration.UserStatus;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Positive;
import jakarta.validation.constraints.Size;
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

    @Size(min = 13, max = 15, message = "short or long phone_number! ")
    @Pattern(regexp = "^((0098)0?9|\\+980?9)[01239]\\d{8}$", message = "invalid phone-number! ")
    String phoneNumber;

    UserStatus status;

    public CustomerUpdateDTO(
            Long id,
            String firstname,
            String lastname,
            String username,
            String email,
            UserStatus status,
            String phoneNumber) {
        super(firstname, lastname, username,email);
        this.id = id;
        this.phoneNumber = phoneNumber;
        this.status = status;
    }
}
