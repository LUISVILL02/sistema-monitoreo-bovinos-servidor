package org.server.apimonitoreo.models.dtos.webSocket;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.UUID;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class SendMensaje {
    private UUID id;
    private  Double latitud;
    private  Double longitud;
}
