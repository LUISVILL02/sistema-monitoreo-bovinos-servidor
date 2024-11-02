package org.server.apimonitoreo.repository;

import org.server.apimonitoreo.models.entities.Usuario;

import java.util.Optional;

public interface UserRepository extends Repository<Usuario> {
    Optional<Usuario> findByCorreo(String correo);
    Boolean existsByCorreo(String correo);
    Boolean existsByIdentificación(Long identificación);
}
