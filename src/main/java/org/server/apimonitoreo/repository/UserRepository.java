package org.server.apimonitoreo.repository;

import org.server.apimonitoreo.models.entities.Usuario;
import org.springframework.data.jpa.repository.Query;

import java.util.Optional;
import java.util.UUID;

public interface UserRepository extends Repository<Usuario> {
    Optional<Usuario> findByIdAndRol_Id(UUID idUser, Integer role);
    Optional<Usuario> findByCorreo(String correo);
    Boolean existsByCorreo(String correo);
    Boolean existsByIdentificación(Long identificación);

    @Query("SELECT f.propietario " +
            "FROM Bovino b " +
            "JOIN b.potrero p " +
            "JOIN p.finca f " +
            "WHERE b.codigo = :bovinoId")
    Optional<Usuario> findByPropietario(String bovinoId);

    @Query("SELECT f.capataz " +
            "FROM Bovino b " +
            "JOIN b.potrero p " +
            "JOIN p.finca f " +
            "WHERE b.codigo = :codigoBovino")
    Optional<Usuario> findByCapataz(String codigoBovino);
}
