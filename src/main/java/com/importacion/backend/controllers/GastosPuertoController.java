package com.importacion.backend.controllers;

import com.importacion.backend.models.entities.gastos_puerto;
import com.importacion.backend.services.GastosPuertoService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/api/gastos-puerto")
public class GastosPuertoController {

    private final GastosPuertoService service;

    public GastosPuertoController(GastosPuertoService service) {
        this.service = service;
    }

    @GetMapping
    public List<gastos_puerto> getAll() {
        return service.findAll();
    }

    @GetMapping("/{id}")
    public gastos_puerto getById(@PathVariable Integer id) {
        return service.findById(id);
    }

    @PostMapping
    public ResponseEntity<gastos_puerto> create(@Valid @RequestBody gastos_puerto entity) {
        return ResponseEntity.status(HttpStatus.CREATED).body(service.create(entity));
    }

    @PutMapping("/{id}")
    public gastos_puerto update(@PathVariable Integer id, @Valid @RequestBody gastos_puerto entity) {
        return service.update(id, entity);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Integer id) {
        service.delete(id);
        return ResponseEntity.noContent().build();
    }
}
