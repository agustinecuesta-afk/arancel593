package com.importacion.backend.controllers;

import com.importacion.backend.models.entities.organismo_control;
import com.importacion.backend.services.OrganismoControlService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/api/organismo-control")
public class OrganismoControlController {

    private final OrganismoControlService service;

    public OrganismoControlController(OrganismoControlService service) {
        this.service = service;
    }

    @GetMapping
    public List<organismo_control> getAll() {
        return service.findAll();
    }

    @GetMapping("/{id}")
    public organismo_control getById(@PathVariable Integer id) {
        return service.findById(id);
    }

    @PostMapping
    public ResponseEntity<organismo_control> create(@Valid @RequestBody organismo_control entity) {
        return ResponseEntity.status(HttpStatus.CREATED).body(service.create(entity));
    }

    @PutMapping("/{id}")
    public organismo_control update(@PathVariable Integer id, @Valid @RequestBody organismo_control entity) {
        return service.update(id, entity);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Integer id) {
        service.delete(id);
        return ResponseEntity.noContent().build();
    }
}
