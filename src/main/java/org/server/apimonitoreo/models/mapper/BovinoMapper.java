package org.server.apimonitoreo.models.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;
import org.server.apimonitoreo.models.dtos.save.BovinoDtoSave;
import org.server.apimonitoreo.models.dtos.send.BovinoDtoSend;
import org.server.apimonitoreo.models.entities.Bovino;

@Mapper(componentModel = "spring")
public interface BovinoMapper extends MapperGen<BovinoDtoSave, BovinoDtoSend, Bovino> {

    @Override
    Bovino dtoSaveToEntity(BovinoDtoSave bovinoDtoSave);

    @Mappings({
            @Mapping(target = "idPotrero", source = "potrero.id"),
            @Mapping(target = "codigoSensor", source = "sensor.id"),
            @Mapping(target = "nombrePropietario", source = "propietario.nombre"),
    })
    @Override
    BovinoDtoSend EntityToDtoSend(Bovino bovino);
}
