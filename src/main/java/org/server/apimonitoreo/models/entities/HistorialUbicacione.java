package org.server.apimonitoreo.models.entities;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.GenericGenerator;

import java.time.LocalDate;
import java.util.UUID;

@Getter
@Setter
@Entity
@Table(name = "Historial_ubicaciones")
public class HistorialUbicacione {
    @Id
    @GeneratedValue(generator = "UUID")
    @Column(name = "id_historial", nullable = false)
    private UUID id;

    @NotNull
    @Column(name = "latitud", nullable = false)
    private Double latitud;

    @NotNull
    @Column(name = "longitud", nullable = false)
    private Double longitud;

    @NotNull
    @Column(name = "fecha", nullable = false)
    private LocalDate fecha;

    @NotNull
    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "id_bovino", nullable = false)
    private Bovino bovino;

    @NotNull
    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "id_sensor", nullable = false)
    private Sensore sensor;

}