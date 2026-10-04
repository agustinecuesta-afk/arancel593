package com.importacion.backend.services;

import com.importacion.backend.exceptions.ResourceNotFoundException;
import com.importacion.backend.models.entities.traslado_carga;
import com.importacion.backend.repositories.TrasladoCargaRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TrasladoCargaService {

    private final TrasladoCargaRepository repository;

    public TrasladoCargaService(TrasladoCargaRepository repository) {
        this.repository = repository;
    }

    public List<traslado_carga> findAll() {
        return repository.findAll();
    }

    public traslado_carga findById(Integer id) {
        return repository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("No se encontró un traslado de carga con id " + id));
    }

    public traslado_carga create(traslado_carga entity) {
        return repository.save(entity);
    }

    public traslado_carga update(Integer id, traslado_carga updated) {
        traslado_carga existing = findById(id);
        existing.setFlete_camion(updated.getFlete_camion());
        existing.setGuardia_armada(updated.getGuardia_armada());
        existing.setEstibadores(updated.getEstibadores());
        return repository.save(existing);
    }

    public void delete(Integer id) {
        if (!repository.existsById(id)) {
            throw new ResourceNotFoundException("No se encontró un traslado de carga con id " + id);
        }
        repository.deleteById(id);
    }
}
