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

    @GetMapping("/{idFinca}")
    @PreAuthorize("hasRole('PROPIETARIO')")
    public ResponseEntity<FincaDtoSend> findByPropietario(@PathVariable UUID idFinca){
        return ResponseEntity.ok(fincaService.findById(idFinca).get());
    }

    @GetMapping("/{idPropietario}/")
    public ResponseEntity<?> findAllByPropietario(@PathVariable UUID idPropietario,
                                                  @RequestParam int page,
                                                  @RequestParam int size){
        return ResponseEntity.ok(fincaService.findAllByPropietario(idPropietario, page, size));
    }

    @PutMapping("/{idFinca}/{idCapataz}")
    @PreAuthorize("hasRole('PROPIETARIO')")
    public ResponseEntity<?> updateCapataz(@PathVariable UUID idFinca,
                                          @PathVariable UUID idCapataz){
        return ResponseEntity.ok(fincaService.updateCapataz(idFinca, idCapataz));
    }

    @GetMapping("/capataz/{idCapataz}")
    @PreAuthorize("hasRole('CAPATAZ')")
    public ResponseEntity<?> findByCapataz(@PathVariable UUID idCapataz){
        return ResponseEntity.ok(fincaService.findByCapataz(idCapataz).get());
    }
}
