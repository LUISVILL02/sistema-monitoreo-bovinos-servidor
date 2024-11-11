package org.server.apimonitoreo.repository;

import org.server.apimonitoreo.models.entities.Usuario;

import java.util.Optional;
import java.util.UUID;

public interface UserRepository extends Repository<Usuario> {
    Optional<Usuario> findByIdAndRol_Id(UUID idUser, Integer role);
    Optional<Usuario> findByCorreo(String correo);
    Boolean existsByCorreo(String correo);
    Boolean existsByIdentificación(Long identificación);
}
