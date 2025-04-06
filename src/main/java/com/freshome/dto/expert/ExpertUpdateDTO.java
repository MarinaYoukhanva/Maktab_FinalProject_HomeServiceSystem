package com.freshome.dto.expert;

import com.freshome.dto.UserUpdateDTO;
import com.freshome.entity.enumeration.UserStatus;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import lombok.Getter;
import lombok.experimental.SuperBuilder;

@Getter
@SuperBuilder
public class ExpertUpdateDTO extends UserUpdateDTO {

    @NotNull(message = "id can not be null for updating! ")
    @Positive
    Long id;
    byte[] profileImage;
    Double score;

    public ExpertUpdateDTO(
            Long id,
            String firstname,
            String lastname,
            String email,
            UserStatus status,
            String phoneNumber,
            byte[] profileImage,
            Double score) {
        super(firstname, lastname, email, status, phoneNumber);
        this.id = id;
        this.profileImage = profileImage;
        this.score = score;
    }
}
