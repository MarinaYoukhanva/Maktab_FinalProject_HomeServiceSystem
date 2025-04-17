package com.freshome.dto.moderator;

import com.freshome.dto.UserCreateDTO;

public class ModeratorCreateDTO extends UserCreateDTO {

    public ModeratorCreateDTO(
            String firstname,
            String lastname,
            String username,
            String password,
            String email) {
        super(firstname, lastname, username, password,email);
    }
}
