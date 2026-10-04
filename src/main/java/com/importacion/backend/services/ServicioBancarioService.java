package com.importacion.backend.services;

import com.importacion.backend.exceptions.ResourceNotFoundException;
import com.importacion.backend.models.entities.servicio_bancario;
import com.importacion.backend.repositories.ServicioBancarioRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ServicioBancarioService {

    private final ServicioBancarioRepository repository;

    public ServicioBancarioService(ServicioBancarioRepository repository) {
        this.repository = repository;
    }

    public List<servicio_bancario> findAll() {
        return repository.findAll();
    }

    public servicio_bancario findById(Integer id) {
        return repository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("No se encontró un servicio bancario con id " + id));
    }

    public servicio_bancario create(servicio_bancario entity) {
        return repository.save(entity);
    }

    public servicio_bancario update(Integer id, servicio_bancario updated) {
        servicio_bancario existing = findById(id);
        existing.setISD(updated.getISD());
        existing.setComision_banco(updated.getComision_banco());
        return repository.save(existing);
    }

    public void delete(Integer id) {
        if (!repository.existsById(id)) {
            throw new ResourceNotFoundException("No se encontró un servicio bancario con id " + id);
        }
        repository.deleteById(id);
    }
}
