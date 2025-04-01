package com.freshome.dto.customer;

import com.freshome.dto.UserCreateDTO;
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
//            LocalDateTime registerDateTime,
//            UserStatus status,
            String phoneNumber) {
        super(firstname, lastname, email, password, phoneNumber);
//        this.creditId = creditId;
    }
}
