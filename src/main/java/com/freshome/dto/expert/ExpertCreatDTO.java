package com.freshome.dto.expert;

import com.freshome.dto.UserCreateDTO;
import com.freshome.entity.enumeration.UserStatus;
import jakarta.validation.constraints.NotNull;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.experimental.FieldDefaults;
import org.springframework.web.multipart.MultipartFile;

import java.time.LocalDateTime;

@Getter
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class ExpertCreatDTO extends UserCreateDTO {

//    @NotNull (message = "profile picture must be uploaded! ")
    MultipartFile profileImage;
//    Double score;
//    Credit credit;

    public ExpertCreatDTO(
            String firstname,
            String lastname,
            String email, String password,
//            LocalDateTime registerDateTime,
//            UserStatus status,
            String phoneNumber,
            MultipartFile profileImage) {
        super(firstname, lastname, email, password, phoneNumber);
        this.profileImage = profileImage;
//        this.score = score;
//        this.credit = credit;
    }
}
