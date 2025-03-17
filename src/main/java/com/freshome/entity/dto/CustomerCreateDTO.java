package com.freshome.entity.dto;

import com.freshome.entity.Customer;
import com.freshome.entity.enumeration.UserStatus;
import lombok.Getter;

import java.time.LocalDateTime;

@Getter
public class CustomerCreateDTO extends UserCreateDTO {

    public CustomerCreateDTO(
            String firstname,
            String lastname,
            String email,
            String password,
            LocalDateTime registerDateTime,
            UserStatus status,
            String phoneNumber) {
        super(firstname, lastname, email, password, registerDateTime, status, phoneNumber);
    }


}
