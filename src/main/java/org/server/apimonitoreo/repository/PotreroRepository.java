package org.server.apimonitoreo.repository;

import org.server.apimonitoreo.models.entities.Potrero;

import java.util.List;
import java.util.UUID;

public interface PotreroRepository extends Repository<Potrero> {
    List<Potrero> findByFinca_Id(UUID idFinca);
}
