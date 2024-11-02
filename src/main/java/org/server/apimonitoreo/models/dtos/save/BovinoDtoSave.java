package org.server.apimonitoreo.models.dtos.save;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.time.LocalDate;

@AllArgsConstructor
@NoArgsConstructor
@Builder
@Data
public class BovinoDtoSave {
    @JsonFormat(pattern = "dd/MM/yyyy")
    private LocalDate fechaNacimiento;
    @JsonFormat(pattern = "dd/MM/yyyy")
    private LocalDate fechaIngreso;
    private char sexo;
    private BigDecimal pesoEntrada;
    private String imagen;
    private String color;
}
