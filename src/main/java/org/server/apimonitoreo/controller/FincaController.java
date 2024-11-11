package org.server.apimonitoreo.controller;

import lombok.AllArgsConstructor;
import org.server.apimonitoreo.models.dtos.save.FincaDtoSave;
import org.server.apimonitoreo.models.dtos.send.FincaDtoSend;
import org.server.apimonitoreo.service.FincaService;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.util.UUID;

@RestController
@RequestMapping("/Api-Monitoreo/V1.0.0/finca")
@AllArgsConstructor
@Validated
public class FincaController {
   private final FincaService fincaService;

   @PostMapping("/{idPropietario}")
   @PreAuthorize("hasRole('PROPIETARIO')")
    public ResponseEntity<?> save(@RequestBody FincaDtoSave fincaDtoSave,
                                             @PathVariable UUID idPropietario){
       URI uri = URI.create("/Api-Monitoreo/V1.0.0/finca/"+idPropietario);
       return ResponseEntity.created(uri).body(fincaService.save(fincaDtoSave, idPropietario));
    }

    @GetMapping("/{idPropietario}")
    @PreAuthorize("hasRole('PROPIETARIO')")
    public ResponseEntity<FincaDtoSend> findByPropietario(@PathVariable UUID idPropietario){
        return ResponseEntity.ok(fincaService.findByPropietario(idPropietario).get());
    }

    @GetMapping("/{idPropietario}/")
    public ResponseEntity<?> findAllByPropietario(@PathVariable UUID idPropietario,
                                                  @RequestParam int page,
                                                  @RequestParam int size){
        return ResponseEntity.ok(fincaService.findAllByPropietario(idPropietario, page, size));
    }
}
