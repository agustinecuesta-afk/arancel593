package com.importacion.backend.repositories;

import com.importacion.backend.models.entities.servicio_bancario;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ServicioBancarioRepository extends JpaRepository<servicio_bancario, Integer> {
}
