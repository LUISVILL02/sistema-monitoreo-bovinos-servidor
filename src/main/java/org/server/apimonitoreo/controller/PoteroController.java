package org.server.apimonitoreo.controller;

import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.server.apimonitoreo.models.dtos.save.CoordenadaDtoSave;
import org.server.apimonitoreo.models.dtos.save.PotreroDtoSave;
import org.server.apimonitoreo.service.PotreroService;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/Api-Monitoreo/V1.0.0/potrero")
@AllArgsConstructor
@Validated
public class PoteroController {
    private final PotreroService potreroService;

    @PostMapping("/{idFinca}")
    @PreAuthorize("hasRole('PROPIETARIO')")
    public ResponseEntity<?> save(@RequestBody @Valid PotreroDtoSave potreroDtoSave,
                                  @RequestBody @Valid List<CoordenadaDtoSave> coordenadaDtoSaves,
                                  @PathVariable UUID idFinca){
        URI uri = URI.create("/Api-Monitoreo/V1.0.0/potrero/"+idFinca);
        return ResponseEntity.created(uri).body(potreroService.save(potreroDtoSave, idFinca, coordenadaDtoSaves));
    }

    @GetMapping
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<?> findAll(){
        return ResponseEntity.ok(potreroService.findAll());
    }

    @GetMapping("/{idFinca}")
    @PreAuthorize("hasRole('PROPIETARIO') or hasRole('CAPATAZ')")
    public ResponseEntity<?> findByFincaId(@PathVariable UUID idFinca){
        return ResponseEntity.ok(potreroService.findByFincaId(idFinca));
    }
}
