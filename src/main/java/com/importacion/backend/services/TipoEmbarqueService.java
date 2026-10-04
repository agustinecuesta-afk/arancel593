package com.importacion.backend.services;

import com.importacion.backend.exceptions.ResourceNotFoundException;
import com.importacion.backend.models.entities.tipo_embarque;
import com.importacion.backend.repositories.TipoEmbarqueRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TipoEmbarqueService {

    private final TipoEmbarqueRepository repository;

    public TipoEmbarqueService(TipoEmbarqueRepository repository) {
        this.repository = repository;
    }

    public List<tipo_embarque> findAll() {
        return repository.findAll();
    }

    public tipo_embarque findById(Integer id) {
        return repository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("No se encontró un tipo de embarque con id " + id));
    }

    public tipo_embarque create(tipo_embarque entity) {
        return repository.save(entity);
    }

    public tipo_embarque update(Integer id, tipo_embarque updated) {
        tipo_embarque existing = findById(id);
        existing.setIdembarque(updated.getIdembarque());
        return repository.save(existing);
    }

    public void delete(Integer id) {
        if (!repository.existsById(id)) {
            throw new ResourceNotFoundException("No se encontró un tipo de embarque con id " + id);
        }
        repository.deleteById(id);
    }
}
