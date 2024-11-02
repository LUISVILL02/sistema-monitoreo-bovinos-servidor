package org.server.apimonitoreo.repository;

import org.server.apimonitoreo.models.entities.Role;
import org.server.apimonitoreo.models.enums.ERole;

import java.util.Optional;

public interface RoleRepository extends Repository<Role> {
    Optional<Role> findByNombre(ERole nombre);
}
