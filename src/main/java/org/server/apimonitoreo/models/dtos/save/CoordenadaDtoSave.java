package org.server.apimonitoreo.models.dtos.save;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class CoordenadaDtoSave {
    private Long latitud;
    private Long longitud;
}
