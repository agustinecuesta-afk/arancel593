package com.importacion.backend.controllers;

import com.importacion.backend.models.entities.traslado_carga;
import com.importacion.backend.services.TrasladoCargaService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/api/traslado-carga")
public class TrasladoCargaController {

    private final TrasladoCargaService service;

    public TrasladoCargaController(TrasladoCargaService service) {
        this.service = service;
    }

    @GetMapping
    public List<traslado_carga> getAll() {
        return service.findAll();
    }

    @GetMapping("/{id}")
    public traslado_carga getById(@PathVariable Integer id) {
        return service.findById(id);
    }

    @PostMapping
    public ResponseEntity<traslado_carga> create(@Valid @RequestBody traslado_carga entity) {
        return ResponseEntity.status(HttpStatus.CREATED).body(service.create(entity));
    }

    @PutMapping("/{id}")
    public traslado_carga update(@PathVariable Integer id, @Valid @RequestBody traslado_carga entity) {
        return service.update(id, entity);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Integer id) {
        service.delete(id);
        return ResponseEntity.noContent().build();
    }
}
