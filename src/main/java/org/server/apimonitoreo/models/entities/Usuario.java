package org.server.apimonitoreo.models.entities;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.*;
import org.hibernate.annotations.GenericGenerator;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

import java.util.*;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Entity
@Table(name = "Usuarios")
public class Usuario{
    @Id
    @GeneratedValue(generator = "UUID")
    @Column(name = "id_usuario", nullable = false)
    private UUID id;

    @Size(max = 255)
    @NotNull
    @Column(name = "nombre", nullable = false)
    private String nombre;

    @Size(max = 255)
    @NotNull
    @Column(name = "apellido", nullable = false)
    private String apellido;

    @NotNull
    @Column(name = "identificación", nullable = false)
    private Long identificación;

    @Size(max = 255)
    @NotNull
    @Column(name = "correo", nullable = false)
    private String correo;

    @Size(max = 255)
    @NotNull
    @Column(name = "contraseña", nullable = false)
    private String contraseña;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_propietario")
    private Usuario propietario;

    @NotNull
    @ManyToOne(fetch = FetchType.EAGER, optional = false)
    @JoinColumn(name = "id_rol", nullable = false)
    private Role rol;

    @OneToMany(mappedBy = "propietario")
    private Set<Finca> fincas = new LinkedHashSet<>();

    @OneToMany(mappedBy = "propietario")
    private Set<Usuario> usuarios = new LinkedHashSet<>();

    @OneToMany(mappedBy = "propietario")
    private Set<Bovino> bovinos = new LinkedHashSet<>();
}