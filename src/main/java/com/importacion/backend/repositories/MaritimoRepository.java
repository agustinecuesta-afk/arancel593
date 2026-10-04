package com.importacion.backend.repositories;

import com.importacion.backend.models.entities.maritimo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface MaritimoRepository extends JpaRepository<maritimo, Integer> {
}
