package com.importacion.backend.controllers;

import com.importacion.backend.models.entities.tipo_embarque;
import com.importacion.backend.services.TipoEmbarqueService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/api/tipo-embarque")
public class TipoEmbarqueController {

    private final TipoEmbarqueService service;

    public TipoEmbarqueController(TipoEmbarqueService service) {
        this.service = service;
    }

    @GetMapping
    public List<tipo_embarque> getAll() {
        return service.findAll();
    }

    @GetMapping("/{id}")
    public tipo_embarque getById(@PathVariable Integer id) {
        return service.findById(id);
    }

    @PostMapping
    public ResponseEntity<tipo_embarque> create(@Valid @RequestBody tipo_embarque entity) {
        return ResponseEntity.status(HttpStatus.CREATED).body(service.create(entity));
    }

    @PutMapping("/{id}")
    public tipo_embarque update(@PathVariable Integer id, @Valid @RequestBody tipo_embarque entity) {
        return service.update(id, entity);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Integer id) {
        service.delete(id);
        return ResponseEntity.noContent().build();
    }
}
