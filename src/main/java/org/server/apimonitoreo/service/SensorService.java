package org.server.apimonitoreo.service;

import org.server.apimonitoreo.models.dtos.send.SensorDtoSend;
import org.server.apimonitoreo.models.entities.Sensore;

public interface SensorService extends Service<Sensore, SensorDtoSend, Sensore>{
    SensorDtoSend save();
}
