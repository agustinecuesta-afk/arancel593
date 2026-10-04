package com.importacion.backend.services;

import com.importacion.backend.exceptions.ResourceNotFoundException;
import com.importacion.backend.models.entities.gastos_puerto;
import com.importacion.backend.repositories.GastosPuertoRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class GastosPuertoService {

    private final GastosPuertoRepository repository;

    public GastosPuertoService(GastosPuertoRepository repository) {
        this.repository = repository;
    }

    public List<gastos_puerto> findAll() {
        return repository.findAll();
    }

    public gastos_puerto findById(Integer id) {
        return repository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("No se encontró un gasto portuario con id " + id));
    }

    public gastos_puerto create(gastos_puerto entity) {
        return repository.save(entity);
    }

    public gastos_puerto update(Integer id, gastos_puerto updated) {
        gastos_puerto existing = findById(id);
        existing.setAlmacenaje(updated.getAlmacenaje());
        existing.setAgente_aduana(updated.getAgente_aduana());
        existing.setRegimen_especial_agente(updated.getRegimen_especial_agente());
        existing.setCandado_satelital(updated.getCandado_satelital());
        return repository.save(existing);
    }

    public void delete(Integer id) {
        if (!repository.existsById(id)) {
            throw new ResourceNotFoundException("No se encontró un gasto portuario con id " + id);
        }
        repository.deleteById(id);
    }
}
