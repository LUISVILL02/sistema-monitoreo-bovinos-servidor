package org.server.apimonitoreo.models.dtos.Auth;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Builder
@Data
public class RegisterDto {
    private String nombre;
    private String apellido;
    private Long identificación;
    private String correo;
    private String contraseña;
    private String propietario;
    private String rol;
}
