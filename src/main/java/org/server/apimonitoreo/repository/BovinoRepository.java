package org.server.apimonitoreo.repository;

import org.server.apimonitoreo.models.entities.Bovino;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface BovinoRepository extends Repository<Bovino> {
    Optional<Bovino> findBySensor_Id(UUID sensorId);
    Optional<Bovino> findByCodigo(String bovinoId);
    List<Bovino> findByPropietario_Id(UUID propietarioId);
    List<Bovino> findByPotrero_Finca_Capataz_Id(UUID capatazId);
}
