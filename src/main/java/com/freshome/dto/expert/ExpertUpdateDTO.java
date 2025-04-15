package com.freshome.dto.expert;

import com.freshome.dto.UserUpdateDTO;
import com.freshome.entity.enumeration.UserStatus;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Positive;
import jakarta.validation.constraints.Size;
import lombok.Getter;

@Getter
public class ExpertUpdateDTO extends UserUpdateDTO {

    @NotNull(message = "id can not be null for updating! ")
    @Positive
    Long id;
//    byte[] profileImage;
    Double score;

    @Size(min = 13, max = 15, message = "short or long phone_number! ")
    @Pattern(regexp = "^((0098)0?9|\\+980?9)[01239]\\d{8}$", message = "invalid phone-number! ")
    String phoneNumber;

    UserStatus status;

    public ExpertUpdateDTO(
            Long id,
            String firstname,
            String lastname,
            String username,
            String email,
            UserStatus status,
            String phoneNumber,
//            byte[] profileImage,
            Double score) {
        super(firstname, lastname, username,email);
        this.id = id;
//        this.profileImage = profileImage;
        this.score = score;
        this.phoneNumber = phoneNumber;
        this.status = status;
    }
}
