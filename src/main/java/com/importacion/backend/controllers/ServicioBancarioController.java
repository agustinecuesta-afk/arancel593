package com.importacion.backend.controllers;

import com.importacion.backend.models.entities.servicio_bancario;
import com.importacion.backend.services.ServicioBancarioService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/api/servicio-bancario")
public class ServicioBancarioController {

    private final ServicioBancarioService service;

    public ServicioBancarioController(ServicioBancarioService service) {
        this.service = service;
    }

    @GetMapping
    public List<servicio_bancario> getAll() {
        return service.findAll();
    }

    @GetMapping("/{id}")
    public servicio_bancario getById(@PathVariable Integer id) {
        return service.findById(id);
    }

    @PostMapping
    public ResponseEntity<servicio_bancario> create(@Valid @RequestBody servicio_bancario entity) {
        return ResponseEntity.status(HttpStatus.CREATED).body(service.create(entity));
    }

    @PutMapping("/{id}")
    public servicio_bancario update(@PathVariable Integer id, @Valid @RequestBody servicio_bancario entity) {
        return service.update(id, entity);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Integer id) {
        service.delete(id);
        return ResponseEntity.noContent().build();
    }
}
