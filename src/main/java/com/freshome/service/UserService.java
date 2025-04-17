package com.freshome.service;

import com.freshome.dto.UserUpdateDTO;
import com.freshome.entity.User;
import org.springframework.security.core.userdetails.UserDetailsService;

import java.util.Optional;

public interface UserService extends UserDetailsService {

    User save(User user);

    Optional<User> findByUsername(String username);

    User update(User user, UserUpdateDTO updateDTO);

    void changePassword(User user, String oldPassword, String newPassword);

    void removeRoleAndDeleteUserIfEmptyRoles(User user, String role);
}
