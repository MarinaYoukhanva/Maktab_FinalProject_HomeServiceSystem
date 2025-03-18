package com.freshome.entity.dto.customer;

import com.freshome.entity.Credit;
import com.freshome.entity.dto.UserCreateDTO;
import com.freshome.entity.enumeration.UserStatus;
import lombok.Getter;

import java.time.LocalDateTime;

@Getter
public class CustomerCreateDTO extends UserCreateDTO {

//    Long creditId;

    public CustomerCreateDTO(
            String firstname,
            String lastname,
            String email,
            String password,
            LocalDateTime registerDateTime,
            UserStatus status,
            String phoneNumber) {
        super(firstname, lastname, email, password, registerDateTime, status, phoneNumber);
//        this.creditId = creditId;
    }
}
