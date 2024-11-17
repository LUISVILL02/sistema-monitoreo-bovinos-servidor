package org.server.apimonitoreo.models.dtos.save;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.UUID;

@AllArgsConstructor
@NoArgsConstructor
@Builder
@Data
public class FincaDtoSave {
    private String nombre;
    private Integer numeroPotreros;
    private Double longitud;
    private Double latitud;
    private UUID idCapataz;
}
