package com.importacion.backend.services;

import com.importacion.backend.exceptions.ResourceNotFoundException;
import com.importacion.backend.models.entities.organismo_control;
import com.importacion.backend.repositories.OrganismoControlRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class OrganismoControlService {

    private final OrganismoControlRepository repository;

    public OrganismoControlService(OrganismoControlRepository repository) {
        this.repository = repository;
    }

    public List<organismo_control> findAll() {
        return repository.findAll();
    }

    public organismo_control findById(Integer id) {
        return repository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("No se encontró un organismo de control con id " + id));
    }

    public organismo_control create(organismo_control entity) {
        return repository.save(entity);
    }

    public organismo_control update(Integer id, organismo_control updated) {
        organismo_control existing = findById(id);
        existing.setTipo_organismo(updated.getTipo_organismo());
        return repository.save(existing);
    }

    public void delete(Integer id) {
        if (!repository.existsById(id)) {
            throw new ResourceNotFoundException("No se encontró un organismo de control con id " + id);
        }
        repository.deleteById(id);
    }
}
