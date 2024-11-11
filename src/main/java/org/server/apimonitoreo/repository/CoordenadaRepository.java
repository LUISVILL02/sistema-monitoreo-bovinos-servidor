package org.server.apimonitoreo.repository;

import org.server.apimonitoreo.models.entities.Coordenada;

import java.util.UUID;

public interface CoordenadaRepository extends Repository<Coordenada>{
    String deleteAllByPotrero_Id(UUID idPotrero);
}
