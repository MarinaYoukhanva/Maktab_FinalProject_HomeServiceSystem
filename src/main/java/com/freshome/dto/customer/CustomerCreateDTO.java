package com.freshome.dto.customer;

import com.freshome.dto.UserCreateDTO;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.Getter;

@Getter
public class CustomerCreateDTO extends UserCreateDTO {

    @Size(min = 13, max = 15, message = "short or long phone_number! ")
    @Pattern(regexp = "^((0098)0?9|\\+980?9)[01239]\\d{8}$", message = "invalid phone-number! ")
    String phoneNumber;

    public CustomerCreateDTO(
            String firstname,
            String lastname,
            String username,
            String password,
            String email,
            String phoneNumber) {
        super(firstname, lastname, username, password,email);
        this.phoneNumber = phoneNumber;
    }
}
