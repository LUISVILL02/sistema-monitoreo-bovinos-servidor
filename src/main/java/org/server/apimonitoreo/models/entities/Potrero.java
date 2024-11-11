package org.server.apimonitoreo.models.entities;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.GenericGenerator;

import java.util.LinkedHashSet;
import java.util.List;
import java.util.Set;
import java.util.UUID;

@Getter
@Setter
@Entity
@Table(name = "Potreros")
public class Potrero {
    @Id
    @GeneratedValue(generator = "UUID")
    @Column(name = "id_potrero", nullable = false)
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

    @OneToMany(mappedBy = "potrero")
    private List<Coordenada> coordenadas;

}