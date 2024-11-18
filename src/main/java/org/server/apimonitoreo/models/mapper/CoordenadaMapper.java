package org.server.apimonitoreo.models.mapper;

import org.mapstruct.Mapper;
import org.server.apimonitoreo.models.dtos.save.CoordenadaDtoSave;
import org.server.apimonitoreo.models.dtos.send.CoordenadaDtoSend;
import org.server.apimonitoreo.models.entities.Coordenada;

@Mapper(componentModel = "spring")
public interface CoordenadaMapper extends MapperGen<CoordenadaDtoSave, CoordenadaDtoSend, Coordenada>{

    Coordenada dtoSaveToEntity(CoordenadaDtoSave s);

    CoordenadaDtoSend EntityToDtoSend(Coordenada e);

    Coordenada dtoSendToEntity(CoordenadaDtoSend m);

}
