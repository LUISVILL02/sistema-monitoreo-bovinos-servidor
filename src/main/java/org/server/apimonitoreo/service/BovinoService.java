package org.server.apimonitoreo.service;

import org.server.apimonitoreo.models.dtos.save.BovinoDtoSave;
import org.server.apimonitoreo.models.dtos.send.BovinoDtoSend;
import org.server.apimonitoreo.models.entities.Bovino;
import org.server.apimonitoreo.models.entities.Usuario;

import java.util.List;
import java.util.UUID;

public interface BovinoService extends Service<BovinoDtoSave, BovinoDtoSend, Bovino> {
    BovinoDtoSend save(BovinoDtoSave bovinoDtoSave);
    Bovino findBySensor_Id(UUID sensorId);
    Usuario findByPropietario(String bovinoId);
    Usuario findByCapataz(String codigoBovino);
    String updateSensor(UUID sensorId, String bovinoId);
    List<BovinoDtoSend> findByPropietarioId(UUID propietarioId);
    List<BovinoDtoSend> findByCapatazId(UUID capatazId);
    BovinoDtoSend findByCodigo(String codigo);
}
