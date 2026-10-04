package com.importacion.backend.repositories;

import com.importacion.backend.models.entities.inpuestos_aduana;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ImpuestosAduanaRepository extends JpaRepository<inpuestos_aduana, Integer> {
}
