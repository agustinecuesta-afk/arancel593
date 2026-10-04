package com.importacion.backend.repositories;

import com.importacion.backend.models.entities.aereo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AereoRepository extends JpaRepository<aereo, Integer> {
}
