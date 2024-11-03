package org.server.apimonitoreo.models.mapper;

import org.mapstruct.Mapper;
import org.server.apimonitoreo.models.dtos.send.SensorDtoSend;
import org.server.apimonitoreo.models.entities.Sensore;

@Mapper(componentModel = "spring")
public interface SensorMapper extends MapperGen<Sensore, SensorDtoSend, Sensore> {
}
