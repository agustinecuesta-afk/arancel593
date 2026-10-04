package com.importacion.backend.controllers;

import com.importacion.backend.models.entities.inpuestos_aduana;
import com.importacion.backend.services.ImpuestosAduanaService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/api/impuestos-aduana")
public class ImpuestosAduanaController {

    private final ImpuestosAduanaService service;

    public ImpuestosAduanaController(ImpuestosAduanaService service) {
        this.service = service;
    }

    @GetMapping
    public List<inpuestos_aduana> getAll() {
        return service.findAll();
    }

    @GetMapping("/{id}")
    public inpuestos_aduana getById(@PathVariable Integer id) {
        return service.findById(id);
    }

    @PostMapping
    public ResponseEntity<inpuestos_aduana> create(@Valid @RequestBody inpuestos_aduana entity) {
        return ResponseEntity.status(HttpStatus.CREATED).body(service.create(entity));
    }

    @PutMapping("/{id}")
    public inpuestos_aduana update(@PathVariable Integer id, @Valid @RequestBody inpuestos_aduana entity) {
        return service.update(id, entity);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Integer id) {
        service.delete(id);
        return ResponseEntity.noContent().build();
    }
}
