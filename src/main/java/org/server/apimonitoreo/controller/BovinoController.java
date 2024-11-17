package org.server.apimonitoreo.controller;

import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.server.apimonitoreo.models.dtos.save.BovinoDtoSave;
import org.server.apimonitoreo.models.dtos.send.BovinoDtoSend;
import org.server.apimonitoreo.service.BovinoService;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Validated
@AllArgsConstructor
@RestController
@RequestMapping("/Api-Monitoreo/V1.0.0/bovinos")
public class BovinoController {
    private final BovinoService bovinoService;

    @GetMapping
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<Page<BovinoDtoSend>> findAll() {
        return ResponseEntity.ok(bovinoService.findAll());
    }

    @PostMapping
    @PreAuthorize("hasRole('PROPIETARIO')")
    public ResponseEntity<BovinoDtoSend> save(@RequestBody @Valid BovinoDtoSave bovinoDtoSave) {
        return ResponseEntity.ok(bovinoService.save(bovinoDtoSave));
    }

    @GetMapping("/{id}")
    public ResponseEntity<BovinoDtoSend> findById(@PathVariable String id) {
        return ResponseEntity.ok(bovinoService.findByCodigo(id));
    }

    @PutMapping("/{idBovino}/{idSensor}")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<String> updateSensor(@PathVariable String idBovino,
                                               @PathVariable UUID idSensor) {
        return ResponseEntity.ok(bovinoService.updateSensor(idSensor, idBovino));
    }

    @GetMapping("/propietario/{idPropietario}")
    @PreAuthorize("hasRole('PROPIETARIO')")
    public ResponseEntity<List<BovinoDtoSend>> findByPropietario(@PathVariable UUID idPropietario) {
        return ResponseEntity.ok(bovinoService.findByPropietarioId(idPropietario));
    }

    @GetMapping("/capataz/{idCapataz}")
    @PreAuthorize("hasRole('CAPATAZ')")
    public ResponseEntity<List<BovinoDtoSend>> findByCapataz(@PathVariable UUID idCapataz) {
        return ResponseEntity.ok(bovinoService.findByCapatazId(idCapataz));
    }
}
