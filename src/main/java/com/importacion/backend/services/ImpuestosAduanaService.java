package com.importacion.backend.services;

import com.importacion.backend.exceptions.ResourceNotFoundException;
import com.importacion.backend.models.entities.inpuestos_aduana;
import com.importacion.backend.repositories.ImpuestosAduanaRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ImpuestosAduanaService {

    private final ImpuestosAduanaRepository repository;

    public ImpuestosAduanaService(ImpuestosAduanaRepository repository) {
        this.repository = repository;
    }

    public List<inpuestos_aduana> findAll() {
        return repository.findAll();
    }

    public inpuestos_aduana findById(Integer id) {
        return repository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("No se encontró un impuesto de aduana con id " + id));
    }

    public inpuestos_aduana create(inpuestos_aduana entity) {
        return repository.save(entity);
    }

    public inpuestos_aduana update(Integer id, inpuestos_aduana updated) {
        inpuestos_aduana existing = findById(id);
        existing.setIva(updated.getIva());
        existing.setFodinfa(updated.getFodinfa());
        existing.setAdvaloren(updated.getAdvaloren());
        existing.setIce(updated.getIce());
        return repository.save(existing);
    }

    public void delete(Integer id) {
        if (!repository.existsById(id)) {
            throw new ResourceNotFoundException("No se encontró un impuesto de aduana con id " + id);
        }
        repository.deleteById(id);
    }
}
