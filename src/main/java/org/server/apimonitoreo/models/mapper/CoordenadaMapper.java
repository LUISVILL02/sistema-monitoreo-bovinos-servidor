package org.server.apimonitoreo.models.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mappings;
import org.server.apimonitoreo.models.dtos.save.CoordenadaDtoSave;
import org.server.apimonitoreo.models.dtos.send.CoordenadaDtoSend;
import org.server.apimonitoreo.models.entities.Coordenada;

@Mapper(componentModel = "spring")
public interface CoordenadaMapper extends MapperGen<CoordenadaDtoSave, CoordenadaDtoSend, Coordenada>{

    Coordenada dtoSaveToEntity(CoordenadaDtoSave s);

    @Mappings({
        @org.mapstruct.Mapping(target = "potrero", source = "potrero.id")
    })
    CoordenadaDtoSend EntityToDtoSend(Coordenada e);

    @Mappings({
        @org.mapstruct.Mapping(target = "potrero.id", source = "potrero")
    })
    Coordenada dtoSendToEntity(CoordenadaDtoSend m);

}
