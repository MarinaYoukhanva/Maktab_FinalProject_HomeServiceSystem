package com.freshome.service;

import com.freshome.dto.UserUpdateDTO;
import com.freshome.entity.User;
import org.springframework.security.core.userdetails.UserDetailsService;

public interface UserService extends UserDetailsService {

    User save(User user);

    User update(User user, UserUpdateDTO updateDTO);

    void changePassword(User user, String oldPassword, String newPassword);

    void removeRoleAndDeleteUserIfEmptyRoles(User user, String role);
}
