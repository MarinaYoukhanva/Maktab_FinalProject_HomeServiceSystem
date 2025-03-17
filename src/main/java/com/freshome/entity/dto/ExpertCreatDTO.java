package com.freshome.entity.dto;

import com.freshome.entity.Credit;
import com.freshome.entity.enumeration.UserStatus;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.experimental.FieldDefaults;

import java.time.LocalDateTime;

@Getter
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class ExpertCreatDTO extends UserCreateDTO{

    byte[] profileImage;
    Long score;
    Credit credit;

    public ExpertCreatDTO(
            String firstname,
            String lastname,
            String email, String password,
            LocalDateTime registerDateTime,
            UserStatus status,
            String phoneNumber,
            byte[] profileImage,
            Long score,
            Credit credit) {
        super(firstname, lastname, email, password, registerDateTime, status, phoneNumber);
        this.profileImage = profileImage;
        this.score = score;
        this.credit = credit;
    }
}
