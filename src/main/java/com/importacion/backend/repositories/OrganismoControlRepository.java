package com.importacion.backend.repositories;

import com.importacion.backend.models.entities.organismo_control;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface OrganismoControlRepository extends JpaRepository<organismo_control, Integer> {
}
