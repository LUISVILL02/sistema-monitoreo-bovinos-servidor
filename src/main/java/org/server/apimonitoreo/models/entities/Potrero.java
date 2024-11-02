package org.server.apimonitoreo.models.entities;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;

import java.util.LinkedHashSet;
import java.util.Set;
import java.util.UUID;

@Getter
@Setter
@Entity
@Table(name = "Potreros")
public class Potrero {
    @Id
    @Column(name = "id_potrero", nullable = false)
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;

    @NotNull
    @Column(name = "longitud", nullable = false)
    private Double longitud;

    @NotNull
    @Column(name = "latitud", nullable = false)
    private Double latitud;

    @NotNull
    @Column(name = "area", nullable = false)
    private Integer area;

    @NotNull
    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "id_finca", nullable = false)
    private Finca finca;

    @OneToMany(mappedBy = "potrero")
    private Set<Bovino> bovinos = new LinkedHashSet<>();

}