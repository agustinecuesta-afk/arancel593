package com.importacion.backend.repositories;

import com.importacion.backend.models.entities.pago_impuestos;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PagoImpuestosRepository extends JpaRepository<pago_impuestos, Integer> {
}
