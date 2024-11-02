package org.server.apimonitoreo.models.dtos.send;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.server.apimonitoreo.models.entities.Bovino;
import org.server.apimonitoreo.models.entities.Finca;

import java.util.LinkedHashSet;
import java.util.Set;
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

    //private Finca idFinca;

    private Set<BovinoDtoSend> bovinos = new LinkedHashSet<>();

}
