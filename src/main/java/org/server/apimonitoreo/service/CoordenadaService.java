package org.server.apimonitoreo.service;

import org.server.apimonitoreo.models.dtos.save.CoordenadaDtoSave;
import org.server.apimonitoreo.models.dtos.send.CoordenadaDtoSend;
import org.server.apimonitoreo.models.entities.Coordenada;

import java.util.List;
import java.util.UUID;

public interface CoordenadaService extends Service<CoordenadaDtoSave, CoordenadaDtoSend, Coordenada> {
    List<CoordenadaDtoSend> findByPotreroId(UUID idPotrero);
    String deleteAllByPotrero_Id(UUID idPotrero);
}
