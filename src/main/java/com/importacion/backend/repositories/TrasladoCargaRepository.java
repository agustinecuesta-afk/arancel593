package com.importacion.backend.repositories;

import com.importacion.backend.models.entities.traslado_carga;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TrasladoCargaRepository extends JpaRepository<traslado_carga, Integer> {
}
