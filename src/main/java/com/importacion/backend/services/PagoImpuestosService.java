package com.importacion.backend.services;

import com.importacion.backend.exceptions.ResourceNotFoundException;
import com.importacion.backend.models.entities.pago_impuestos;
import com.importacion.backend.repositories.PagoImpuestosRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PagoImpuestosService {

    private final PagoImpuestosRepository repository;

    public PagoImpuestosService(PagoImpuestosRepository repository) {
        this.repository = repository;
    }

    public List<pago_impuestos> findAll() {
        return repository.findAll();
    }

    public pago_impuestos findById(Integer id) {
        return repository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("No se encontró un pago de impuestos con id " + id));
    }

    public pago_impuestos create(pago_impuestos entity) {
        return repository.save(entity);
    }

    public pago_impuestos update(Integer id, pago_impuestos updated) {
        pago_impuestos existing = findById(id);
        existing.setEfectivo(updated.getEfectivo());
        return repository.save(existing);
    }

    public void delete(Integer id) {
        if (!repository.existsById(id)) {
            throw new ResourceNotFoundException("No se encontró un pago de impuestos con id " + id);
        }
        repository.deleteById(id);
    }
}
