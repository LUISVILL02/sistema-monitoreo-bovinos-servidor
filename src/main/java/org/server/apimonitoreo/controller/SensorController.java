package org.server.apimonitoreo.controller;

import lombok.AllArgsConstructor;
import org.server.apimonitoreo.service.SensorService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@RestController
@RequestMapping("/Api-Monitoreo/V1.0.0/sensor")
@AllArgsConstructor
public class SensorController {
    private final SensorService sensorService;

    @GetMapping("/{id}")
    public ResponseEntity<?> getSensorById(@PathVariable UUID id){
        try {
            return ResponseEntity.ok().body(sensorService.findById(id));
        } catch (Exception e) {
            return ResponseEntity.badRequest().body("Error al buscar el sensor. Mas detalles: "+e.getMessage());
        }
    }
}
