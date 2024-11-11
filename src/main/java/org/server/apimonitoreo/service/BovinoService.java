package org.server.apimonitoreo.service;

import org.server.apimonitoreo.models.dtos.save.BovinoDtoSave;
import org.server.apimonitoreo.models.dtos.send.BovinoDtoSend;
import org.server.apimonitoreo.models.entities.Bovino;

public interface BovinoService extends Service<BovinoDtoSave, BovinoDtoSend, Bovino> {
    BovinoDtoSend save(BovinoDtoSave bovinoDtoSave);
}
