package org.server.apimonitoreo.models.dtos.save;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Builder
@Data
public class FincaDtoSave {
    private String nombre;
    private Integer numeroPotreros;
    private Double longitud;
    private Double latitud;
}
