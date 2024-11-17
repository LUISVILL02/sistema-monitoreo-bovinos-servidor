package org.server.apimonitoreo.service;

import org.server.apimonitoreo.models.dtos.save.FincaDtoSave;
import org.server.apimonitoreo.models.dtos.send.FincaDtoSend;
import org.server.apimonitoreo.models.entities.Finca;
import org.springframework.data.domain.Page;

import java.util.Optional;
import java.util.UUID;


public interface FincaService extends Service<FincaDtoSave, FincaDtoSend, Finca> {
    FincaDtoSend save(FincaDtoSave fincaDtoSave, UUID idPropietario);
    Page<FincaDtoSend> findAllByPropietario(UUID idPropietario, int page, int size);
    FincaDtoSend updateCapataz(UUID idfinca, UUID capatazId);
    Optional<FincaDtoSend> findByCapataz(UUID idCapataz);
}
