package org.server.apimonitoreo.models.dtos.send;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.LinkedHashSet;
import java.util.Set;
import java.util.UUID;

@AllArgsConstructor
@NoArgsConstructor
@Builder
@Data
public class FincaDtoSend {
    private UUID id;
    private String nombre;
    private Integer numeroPotreros;
    private Double longitud;
    private Double latitud;
    private String nombrePropietario;
    private String nombreCapataz;
    private Set<PotreroDtoSend> potreros = new LinkedHashSet<>();
}
