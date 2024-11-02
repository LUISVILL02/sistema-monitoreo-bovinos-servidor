package org.server.apimonitoreo.models.dtos.send;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.server.apimonitoreo.models.entities.Role;

import java.util.LinkedHashSet;
import java.util.Set;
import java.util.UUID;

@AllArgsConstructor
@NoArgsConstructor
@Builder
@Data
public class UsuarioDtoSend {
    private UUID id;
    private String nombre;
    private String apellido;
    private Long identificación;
    private String correo;
    private UUID idPropietario;
    private Role idRol;
    private Set<FincaDtoSend> fincas = new LinkedHashSet<>();
    private Set<UsuarioDtoSend> usuarios = new LinkedHashSet<>();
}
