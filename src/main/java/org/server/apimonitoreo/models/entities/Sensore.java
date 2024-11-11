package org.server.apimonitoreo.models.entities;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.GenericGenerator;

import java.util.LinkedHashSet;
import java.util.Set;
import java.util.UUID;

@Getter
@Setter
@Entity
@Table(name = "Sensores")
public class Sensore {
    @Id
    @GeneratedValue(generator = "UUID")
    @Column(name = "codigo", nullable = false)
    private UUID id;

    @OneToOne(mappedBy = "sensor")
    private Bovino bovino;

    @OneToMany(mappedBy = "sensor")
    private Set<HistorialUbicacione> historialUbicaciones = new LinkedHashSet<>();

}