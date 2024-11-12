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
    public ResponseEntity<BovinoDtoSend> save(@RequestBody @Valid BovinoDtoSave bovinoDtoSave) {
        return ResponseEntity.ok(bovinoService.save(bovinoDtoSave));
    }

    @GetMapping("/{id}")
    public ResponseEntity<Optional<BovinoDtoSend>> findById(@PathVariable UUID id) {
        return ResponseEntity.ok(bovinoService.findById(id));
    }

    @PutMapping("/{idBovino}/{idSensor}")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<String> updateSensor(@PathVariable String idBovino,
                                               @PathVariable UUID idSensor) {
        return ResponseEntity.ok(bovinoService.updateSensor(idSensor, idBovino));
    }
}
