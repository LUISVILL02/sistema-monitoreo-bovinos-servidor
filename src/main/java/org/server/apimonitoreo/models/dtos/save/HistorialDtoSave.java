package org.server.apimonitoreo.models.dtos.save;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@AllArgsConstructor
@NoArgsConstructor
@Builder
@Data
public class HistorialDtoSave {
    private Double latitud;
    private Double longitud;
    private LocalDate fecha;
}
