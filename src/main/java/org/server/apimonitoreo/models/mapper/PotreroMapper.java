package org.server.apimonitoreo.models.mapper;

import org.mapstruct.Mapper;
import org.server.apimonitoreo.models.dtos.save.PotreroDtoSave;
import org.server.apimonitoreo.models.dtos.send.PotreroDtoSend;
import org.server.apimonitoreo.models.entities.Potrero;

@Mapper(componentModel = "spring")
public interface PotreroMapper extends MapperGen<PotreroDtoSave, PotreroDtoSend, Potrero>{

}
