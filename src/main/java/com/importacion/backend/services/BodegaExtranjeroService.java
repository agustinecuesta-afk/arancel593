package com.importacion.backend.services;

import com.importacion.backend.exceptions.ResourceNotFoundException;
import com.importacion.backend.models.entities.bodega_extranjero;
import com.importacion.backend.repositories.BodegaExtranjeroRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class BodegaExtranjeroService {

    private final BodegaExtranjeroRepository repository;

    public BodegaExtranjeroService(BodegaExtranjeroRepository repository) {
        this.repository = repository;
    }

    public List<bodega_extranjero> findAll() {
        return repository.findAll();
    }

    public bodega_extranjero findById(Integer id) {
        return repository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("No se encontró una bodega extranjera con id " + id));
    }

    public bodega_extranjero create(bodega_extranjero entity) {
        return repository.save(entity);
    }

    public bodega_extranjero update(Integer id, bodega_extranjero updated) {
        bodega_extranjero existing = findById(id);
        existing.setPais(updated.getPais());
        existing.setNombre(updated.getNombre());
        existing.setEntrada(updated.getEntrada());
        existing.setEtiqueta(updated.getEtiqueta());
        existing.setManejo(updated.getManejo());
        existing.setSalida(updated.getSalida());
        return repository.save(existing);
    }

    public void delete(Integer id) {
        if (!repository.existsById(id)) {
            throw new ResourceNotFoundException("No se encontró una bodega extranjera con id " + id);
        }
        repository.deleteById(id);
    }
}
