package com.importacion.backend.repositories;

import com.importacion.backend.models.entities.bodega_extranjero;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface BodegaExtranjeroRepository extends JpaRepository<bodega_extranjero, Integer> {
}
