package dev.reddy.olm.service;

import dev.reddy.olm.entity.Role;

import java.util.Optional;

public interface RoleService {
    Optional<Role> findRoleByName(String name);
}
