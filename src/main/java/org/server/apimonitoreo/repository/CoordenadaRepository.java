package org.server.apimonitoreo.repository;

import org.server.apimonitoreo.models.entities.Coordenada;
import org.server.apimonitoreo.models.entities.Potrero;

import java.util.UUID;

public interface CoordenadaRepository extends Repository<Coordenada>{
    String deleteAllByPotrero_Id(UUID idPotrero);
    Potrero findByPotrero_Id(UUID idPotrero);
}
