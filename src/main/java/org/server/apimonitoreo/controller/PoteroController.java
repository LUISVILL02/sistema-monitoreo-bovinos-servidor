package org.server.apimonitoreo.controller;

import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.server.apimonitoreo.models.dtos.save.CoordenadaDtoSave;
import org.server.apimonitoreo.models.dtos.save.PotreroDtoSave;
import org.server.apimonitoreo.models.dtos.save.PotreroSave;
import org.server.apimonitoreo.service.PotreroService;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.util.UUID;

@RestController
@RequestMapping("/Api-Monitoreo/V1.0.0/potrero")
@AllArgsConstructor
@Validated
public class PoteroController {
    private final PotreroService potreroService;

    @PostMapping("/{idFinca}")
    @PreAuthorize("hasRole('PROPIETARIO')")
    public ResponseEntity<?> save(@RequestBody @Valid PotreroSave potreroDto,
                                  @PathVariable UUID idFinca){
        System.out.println("El body es: "+potreroDto);
        URI uri = URI.create("/Api-Monitoreo/V1.0.0/potrero/"+idFinca);
        return ResponseEntity.created(uri).body(potreroService.save(
                potreroDto.getPotrero(), idFinca, potreroDto.getCoordenadas()));
    }

    @GetMapping
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<?> findAll(){
        return ResponseEntity.ok(potreroService.findAll());
    }

    @GetMapping("/{idFinca}")
    @PreAuthorize("hasRole('PROPIETARIO') or hasRole('CAPATAZ')")
    public ResponseEntity<?> findByFincaId(@PathVariable UUID idFinca){
        System.out.println(potreroService.findByFincaId(idFinca));
        return ResponseEntity.ok(potreroService.findByFincaId(idFinca));
    }
}
