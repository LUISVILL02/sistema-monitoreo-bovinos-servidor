package org.server.apimonitoreo.models.dtos.send;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Builder
@Data
public class RolDtoSend {
    private Integer id;
    private String nombre;

    //private Set<Usuario> usuarios = new LinkedHashSet<>();
}
