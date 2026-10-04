package com.importacion.backend.controllers;

import com.importacion.backend.models.entities.maritimo;
import com.importacion.backend.services.MaritimoService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/api/maritimo")
public class MaritimoController {

    private final MaritimoService service;

    public MaritimoController(MaritimoService service) {
        this.service = service;
    }

    @GetMapping
    public List<maritimo> getAll() {
        return service.findAll();
    }

    @GetMapping("/{id}")
    public maritimo getById(@PathVariable Integer id) {
        return service.findById(id);
    }

    @PostMapping
    public ResponseEntity<maritimo> create(@Valid @RequestBody maritimo entity) {
        return ResponseEntity.status(HttpStatus.CREATED).body(service.create(entity));
    }

    @PutMapping("/{id}")
    public maritimo update(@PathVariable Integer id, @Valid @RequestBody maritimo entity) {
        return service.update(id, entity);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Integer id) {
        service.delete(id);
        return ResponseEntity.noContent().build();
    }
}
