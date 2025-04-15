package com.freshome.dto.expert;

import com.freshome.dto.UserCreateDTO;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.experimental.FieldDefaults;
import org.springframework.web.multipart.MultipartFile;

@Getter
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class ExpertCreatDTO extends UserCreateDTO {

    @Size(min = 13, max = 15, message = "short or long phone_number! ")
    @Pattern(regexp = "^((0098)0?9|\\+980?9)[01239]\\d{8}$", message = "invalid phone-number! ")
    String phoneNumber;

    //    @NotNull (message = "profile picture must be uploaded! ")
    MultipartFile profileImage;


    public ExpertCreatDTO(
            String firstname,
            String lastname,
            String username,
            String password,
            String email,
            String phoneNumber,
            MultipartFile profileImage) {
        super(firstname, lastname, username, password,email);
        this.phoneNumber = phoneNumber;
        this.profileImage = profileImage;
    }
}
