package org.server.apimonitoreo.models.dtos.send;


import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@AllArgsConstructor
@NoArgsConstructor
@Builder
@Data
public class BovinoDtoSend {
    private String codigo;
    @JsonFormat(pattern = "dd/MM/yyyy")
    private LocalDate fechaNacimiento;
    @JsonFormat(pattern = "dd/MM/yyyy")
    private LocalDate fechaIngreso;
    @JsonFormat(pattern = "dd/MM/yyyy")
    private LocalDate fechaSalida;
    private String sexo;
    private BigDecimal pesoEntrada;
    private String imagen;
    private String color;

    private UUID idPotrero;
    private UUID codigoSensor;
    private List<HistorialDtoSend> historialUbicaciones = new ArrayList<>();
}
