package org.server.apimonitoreo.repository;

import org.server.apimonitoreo.models.entities.Finca;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.Optional;
import java.util.UUID;

public interface FincaRepository extends Repository<Finca> {
    Optional<Finca> findByPropietario_Id(UUID idPropietario);
    Page<Finca> findAllByPropietario_Id(UUID idPropietario, Pageable pageable);
}
