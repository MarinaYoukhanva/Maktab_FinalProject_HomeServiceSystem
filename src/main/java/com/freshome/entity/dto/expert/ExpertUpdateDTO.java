package com.freshome.entity.dto.expert;

import com.freshome.entity.dto.UserCreateDTO;
import com.freshome.entity.enumeration.UserStatus;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import lombok.Getter;

import java.time.LocalDateTime;

@Getter
public class ExpertUpdateDTO extends UserCreateDTO {

    @NotNull(message = "id can not be null for updating! ")
    @Positive
    Long id;
    byte[] profileImage;
    Long score;

    public ExpertUpdateDTO(
            Long id,
            String firstname,
            String lastname,
            String email,
            String password,
            LocalDateTime registerDateTime,
            UserStatus status,
            String phoneNumber,
            byte[] profileImage,
            Long score) {
        super(firstname, lastname, email, password, registerDateTime, status, phoneNumber);
        this.id = id;
        this.profileImage = profileImage;
        this.score = score;
    }
}
