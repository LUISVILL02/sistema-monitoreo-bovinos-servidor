package org.server.apimonitoreo.models.entities;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.GenericGenerator;

import java.util.UUID;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Entity
@Table(name = "Coordenadas")
public class Coordenada {
    @Id
    @GeneratedValue(generator = "UUID")
    @Column(name = "id_coordenada", nullable = false)
    private UUID id;
    private Long latitud;
    private Long longitud;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "id_potrero", nullable = false)
    private Potrero potrero;
}
