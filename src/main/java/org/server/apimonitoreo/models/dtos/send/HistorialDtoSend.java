package org.server.apimonitoreo.models.dtos.send;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.util.UUID;

@AllArgsConstructor
@NoArgsConstructor
@Builder
@Data
public class HistorialDtoSend {
    private UUID id;
    private Double latitud;
    private Double longitud;
    private LocalDate fecha;
    private String codigoBovino;
    private UUID idSensor;
}
