package com.importacion.backend.repositories;

import com.importacion.backend.models.entities.tipo_embarque;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TipoEmbarqueRepository extends JpaRepository<tipo_embarque, Integer> {
}
