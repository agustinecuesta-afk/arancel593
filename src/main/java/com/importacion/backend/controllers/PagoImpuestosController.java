package com.importacion.backend.controllers;

import com.importacion.backend.models.entities.pago_impuestos;
import com.importacion.backend.services.PagoImpuestosService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/api/pago-impuestos")
public class PagoImpuestosController {

    private final PagoImpuestosService service;

    public PagoImpuestosController(PagoImpuestosService service) {
        this.service = service;
    }

    @GetMapping
    public List<pago_impuestos> getAll() {
        return service.findAll();
    }

    @GetMapping("/{id}")
    public pago_impuestos getById(@PathVariable Integer id) {
        return service.findById(id);
    }

    @PostMapping
    public ResponseEntity<pago_impuestos> create(@Valid @RequestBody pago_impuestos entity) {
        return ResponseEntity.status(HttpStatus.CREATED).body(service.create(entity));
    }

    @PutMapping("/{id}")
    public pago_impuestos update(@PathVariable Integer id, @Valid @RequestBody pago_impuestos entity) {
        return service.update(id, entity);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Integer id) {
        service.delete(id);
        return ResponseEntity.noContent().build();
    }
}
