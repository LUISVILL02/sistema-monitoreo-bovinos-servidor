package org.server.apimonitoreo.models.dtos.send;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.UUID;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class CoordenadaDtoSend {
    private UUID id;
    private Long latitud;
    private Long longitud;
    private UUID potrero;
}
