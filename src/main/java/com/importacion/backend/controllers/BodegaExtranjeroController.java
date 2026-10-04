package com.importacion.backend.controllers;

import com.importacion.backend.models.entities.bodega_extranjero;
import com.importacion.backend.services.BodegaExtranjeroService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/api/bodega-extranjero")
public class BodegaExtranjeroController {

    private final BodegaExtranjeroService service;

    public BodegaExtranjeroController(BodegaExtranjeroService service) {
        this.service = service;
    }

    @GetMapping
    public List<bodega_extranjero> getAll() {
        return service.findAll();
    }

    @GetMapping("/{id}")
    public bodega_extranjero getById(@PathVariable Integer id) {
        return service.findById(id);
    }

    @PostMapping
    public ResponseEntity<bodega_extranjero> create(@Valid @RequestBody bodega_extranjero entity) {
        return ResponseEntity.status(HttpStatus.CREATED).body(service.create(entity));
    }

    @PutMapping("/{id}")
    public bodega_extranjero update(@PathVariable Integer id, @Valid @RequestBody bodega_extranjero entity) {
        return service.update(id, entity);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Integer id) {
        service.delete(id);
        return ResponseEntity.noContent().build();
    }
}
