package org.server.apimonitoreo.models.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;
import org.server.apimonitoreo.models.dtos.save.FincaDtoSave;
import org.server.apimonitoreo.models.dtos.send.FincaDtoSend;
import org.server.apimonitoreo.models.entities.Finca;

@Mapper(componentModel = "spring")
public interface FincaMapper extends MapperGen<FincaDtoSave, FincaDtoSend, Finca> {
    @Mappings({
            @Mapping(target = "nombrePropietario", source = "propietario.nombre"),
            @Mapping(target = "nombreCapataz", source = "capataz.nombre")
    })
    FincaDtoSend EntityToDtoSend(Finca finca);
    Finca DtoSaveToEntity(FincaDtoSave fincaDtoSave);
}
