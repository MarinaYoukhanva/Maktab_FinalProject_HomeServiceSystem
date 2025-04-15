package com.freshome.service.impl;

import com.freshome.entity.Role;
import com.freshome.exception.NotFoundException;
import com.freshome.repository.RoleRepository;
import com.freshome.service.RoleService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;


@Service
@RequiredArgsConstructor
public class RoleServiceImpl implements RoleService {

    private final RoleRepository roleRepository;

    @Override
    public Role findByName(String name) {
        return roleRepository.findByName(name)
                .orElseThrow(() -> new NotFoundException("Role with name " + name + " not found!"));
    }
}
