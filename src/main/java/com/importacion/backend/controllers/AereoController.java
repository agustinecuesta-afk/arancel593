package com.importacion.backend.controllers;

import com.importacion.backend.models.entities.aereo;
import com.importacion.backend.services.AereoService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/api/aereo")
public class AereoController {

    private final AereoService service;

    public AereoController(AereoService service) {
        this.service = service;
    }

    @GetMapping
    public List<aereo> getAll() {
        return service.findAll();
    }

    @GetMapping("/{id}")
    public aereo getById(@PathVariable Integer id) {
        return service.findById(id);
    }

    @PostMapping
    public ResponseEntity<aereo> create(@Valid @RequestBody aereo entity) {
        aereo saved = service.create(entity);
        return ResponseEntity.status(HttpStatus.CREATED).body(saved);
    }

    @PutMapping("/{id}")
    public aereo update(@PathVariable Integer id, @Valid @RequestBody aereo entity) {
        return service.update(id, entity);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Integer id) {
        service.delete(id);
        return ResponseEntity.noContent().build();
    }
}
