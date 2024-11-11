package org.server.apimonitoreo.service;

import org.server.apimonitoreo.models.dtos.save.CoordenadaDtoSave;
import org.server.apimonitoreo.models.dtos.save.PotreroDtoSave;
import org.server.apimonitoreo.models.dtos.send.PotreroDtoSend;
import org.server.apimonitoreo.models.entities.Potrero;

import java.util.List;
import java.util.UUID;

public interface PotreroService extends Service<PotreroDtoSave, PotreroDtoSend, Potrero> {
    PotreroDtoSend save(PotreroDtoSave potreroDtoSave, UUID idFinca, List<CoordenadaDtoSave> coordenadas);
    List<PotreroDtoSend> findByFincaId(UUID idFinca);
}
