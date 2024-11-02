package org.server.apimonitoreo.models.entities;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.*;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.LinkedHashSet;
import java.util.Set;

@AllArgsConstructor
@NoArgsConstructor
@Builder
@Data
@Entity
@Table(name = "Bovinos")
public class Bovino {
    @Id
    @Size(max = 255)
    @Column(name = "codigo", nullable = false)
    @GeneratedValue(strategy = GenerationType.UUID)
    private String codigo;

    @NotNull
    @Column(name = "fecha_nacimiento", nullable = false)
    private LocalDate fechaNacimiento;

    @NotNull
    @Column(name = "fecha_ingreso", nullable = false)
    private LocalDate fechaIngreso;

    @NotNull
    @Column(name = "fecha_salida", nullable = false)
    private LocalDate fechaSalida;

    @Size(max = 255)
    @NotNull
    @Column(name = "sexo", nullable = false)
    private String sexo;

    @NotNull
    @Column(name = "peso_entrada", nullable = false, precision = 8, scale = 2)
    private BigDecimal pesoEntrada;

    @Size(max = 255)
    @Column(name = "imagen")
    private String imagen;

    @Size(max = 255)
    @Column(name = "color")
    private String color;

    @NotNull
    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "id_potrero", nullable = false)
    private Potrero potrero;

    @NotNull
    @OneToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "codigo", referencedColumnName = "id")
    private Sensore sensor;

    @OneToMany(mappedBy = "bovino")
    private Set<HistorialUbicacione> historialUbicaciones = new LinkedHashSet<>();

}