package org.server.apimonitoreo.models.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;
import org.server.apimonitoreo.models.dtos.send.HistorialDtoSend;
import org.server.apimonitoreo.models.entities.HistorialUbicacione;

@Mapper(componentModel = "spring")
public interface HistorialMapper extends MapperGen<Object, HistorialDtoSend, HistorialUbicacione> {

    @Mappings({
            @Mapping(target = "codigoBovino", source = "bovino.codigo"),
            @Mapping(target = "idSensor", source = "sensor.id")
    })
    @Override
    HistorialDtoSend EntityToDtoSend(HistorialUbicacione historial);
}
