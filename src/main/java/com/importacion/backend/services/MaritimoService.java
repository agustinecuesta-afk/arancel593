package com.importacion.backend.services;

import com.importacion.backend.exceptions.ResourceNotFoundException;
import com.importacion.backend.models.entities.maritimo;
import com.importacion.backend.repositories.MaritimoRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class MaritimoService {

    private final MaritimoRepository repository;

    public MaritimoService(MaritimoRepository repository) {
        this.repository = repository;
    }

    public List<maritimo> findAll() {
        return repository.findAll();
    }

    public maritimo findById(Integer id) {
        return repository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("No se encontró un registro marítimo con id " + id));
    }

    public maritimo create(maritimo entity) {
        return repository.save(entity);
    }

    public maritimo update(Integer id, maritimo updated) {
        maritimo existing = findById(id);
        existing.setCubicaje(updated.getCubicaje());
        existing.setPrecio(updated.getPrecio());
        return repository.save(existing);
    }

    public void delete(Integer id) {
        if (!repository.existsById(id)) {
            throw new ResourceNotFoundException("No se encontró un registro marítimo con id " + id);
        }
        repository.deleteById(id);
    }
}
