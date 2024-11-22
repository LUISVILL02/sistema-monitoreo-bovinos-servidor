package org.server.apimonitoreo.controller;

import lombok.AllArgsConstructor;
import org.server.apimonitoreo.service.HistorialUbiService;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/Api-Monitoreo/V1.0.0/historial")
@AllArgsConstructor
public class HistorialUbiController {
    private final HistorialUbiService historialUbiService;

    @GetMapping("/{codigoBovino}")
    @PreAuthorize("hasRole('PROPIETARIO') or hasRole('CAPATAZ')")
    public ResponseEntity<?> getHistorial(@PathVariable String codigoBovino) {
        return ResponseEntity.ok(historialUbiService.findByBovino_Codigo(codigoBovino));
    }
}
