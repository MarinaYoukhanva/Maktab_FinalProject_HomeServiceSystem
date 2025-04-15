package com.freshome.service.impl;

import com.freshome.dto.UserUpdateDTO;
import com.freshome.entity.Role;
import com.freshome.entity.User;
import com.freshome.exception.ChangePasswordException;
import com.freshome.exception.ExistenceException;
import com.freshome.repository.UserRepository;
import com.freshome.service.RoleService;
import com.freshome.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.util.Set;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;
    private final RoleService roleService;
    private final PasswordEncoder passwordEncoder;

    @Override
    public UserDetails loadUserByUsername(String username) {
        return userRepository.findByUsername(username)
                .orElseThrow(() -> new UsernameNotFoundException(username));
    }

    @Override
    public User save(User user) {
        if (userRepository.existsByUsername(user.getUsername()))
            throw new ExistenceException(user.getUsername());
        if(userRepository.existsByEmail(user.getEmail()))
            throw new ExistenceException(user.getEmail());
        user.setPassword(
                passwordEncoder.encode(user.getPassword()));
        return userRepository.save(user);
    }

    @Override
    public User update(User user, UserUpdateDTO updateDTO) {
        if (StringUtils.hasText(updateDTO.getFirstname()))
            user.setFirstname(updateDTO.getFirstname());

        if (StringUtils.hasText(updateDTO.getLastname()))
            user.setLastname(updateDTO.getLastname());

        if (StringUtils.hasText(updateDTO.getUsername())) {
            if (userRepository.existsByUsername(updateDTO.getUsername()))
                throw new ExistenceException(updateDTO.getUsername());
            user.setUsername(updateDTO.getUsername());
        }
        if (StringUtils.hasText(updateDTO.getEmail())){
            if (userRepository.existsByEmail(updateDTO.getEmail()))
                throw new ExistenceException(updateDTO.getEmail());
            user.setEmail(updateDTO.getEmail());
        }
        return userRepository.save(user);
    }

    @Override
    public void changePassword(User user, String oldPassword, String newPassword) {
        if (oldPassword.equals(newPassword)
                || !passwordEncoder.matches(oldPassword, user.getPassword()))
            throw new ChangePasswordException();
        user.setPassword(passwordEncoder.encode(newPassword));
        userRepository.save(user);
    }

    @Override
    public void removeRoleAndDeleteUserIfEmptyRoles(User user, String role) {
        Set<Role> roles = user.getRoles();
        roles.remove(roleService.findByName(role));
        if (roles.isEmpty())
            user.setDeleted(true);
        userRepository.save(user);
    }
}
