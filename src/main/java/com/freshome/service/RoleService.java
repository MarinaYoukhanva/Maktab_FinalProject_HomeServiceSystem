package com.freshome.service;

import com.freshome.entity.Role;

public interface RoleService {
    Role findByName(String name);
}
