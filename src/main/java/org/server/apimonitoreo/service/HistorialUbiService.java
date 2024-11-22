package org.server.apimonitoreo.service;

import org.server.apimonitoreo.models.dtos.save.HistorialDtoSave;
import org.server.apimonitoreo.models.dtos.send.HistorialDtoSend;
import org.server.apimonitoreo.models.entities.HistorialUbicacione;

import java.util.List;
import java.util.UUID;

public interface HistorialUbiService extends Service<HistorialDtoSave, HistorialDtoSend, HistorialUbicacione> {
    void save(HistorialDtoSave historial, UUID codigoSensor, String CodigoBovino);
    List<HistorialDtoSend> findByBovino_Codigo(String codigoBovino);
}
