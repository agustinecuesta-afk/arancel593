package com.importacion.backend.services;

import com.importacion.backend.exceptions.ResourceNotFoundException;
import com.importacion.backend.models.entities.aereo;
import com.importacion.backend.repositories.AereoRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AereoService {

    private final AereoRepository repository;

    public AereoService(AereoRepository repository) {
        this.repository = repository;
    }

    public List<aereo> findAll() {
        return repository.findAll();
    }

    public aereo findById(Integer id) {
        return repository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("No se encontró un registro aereo con id " + id));
    }

    public aereo create(aereo entity) {
        return repository.save(entity);
    }

    public aereo update(Integer id, aereo updated) {
        aereo existing = findById(id);
        existing.setPeso(updated.getPeso());
        existing.setPrecio(updated.getPrecio());
        return repository.save(existing);
    }

    public void delete(Integer id) {
        if (!repository.existsById(id)) {
            throw new ResourceNotFoundException("No se encontró un registro aereo con id " + id);
        }
        repository.deleteById(id);
    }
}
