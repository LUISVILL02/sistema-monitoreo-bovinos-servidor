package org.server.apimonitoreo.models.entities;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.LinkedHashSet;
import java.util.Set;
import java.util.UUID;

@Getter
@Setter
@Entity
@Table(name = "Sensores")
public class Sensore {
    @Id
    @Column(name = "codigo", nullable = false)
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;

    @OneToOne(mappedBy = "sensor")
    private Bovino bovino;

    @OneToMany(mappedBy = "sensor")
    private Set<HistorialUbicacione> historialUbicaciones = new LinkedHashSet<>();

}