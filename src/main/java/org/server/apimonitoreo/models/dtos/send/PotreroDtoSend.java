package org.server.apimonitoreo.models.dtos.send;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@AllArgsConstructor
@NoArgsConstructor
@Builder
@Data
public class PotreroDtoSend {
    private UUID id;
    private Double longitud;
    private Double latitud;
    private Integer area;
    private List<CoordenadaDtoSend> coordenadas = new ArrayList<>();
    private UUID idFinca;
}
