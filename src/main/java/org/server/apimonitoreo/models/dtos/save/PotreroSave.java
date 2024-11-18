package org.server.apimonitoreo.models.dtos.save;


import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Builder
@Data
public class PotreroSave {
    private PotreroDtoSave potrero;
    private List<CoordenadaDtoSave> coordenadas;
}
