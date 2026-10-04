package com.importacion.backend.repositories;

import com.importacion.backend.models.entities.gastos_puerto;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface GastosPuertoRepository extends JpaRepository<gastos_puerto, Integer> {
}
