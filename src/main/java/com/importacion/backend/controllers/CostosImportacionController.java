package com.importacion.backend.controllers;

import com.importacion.backend.dto.CostoResumenResponse;
import com.importacion.backend.services.CostosImportacionService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/costos")
public class CostosImportacionController {

    private final CostosImportacionService service;

    public CostosImportacionController(CostosImportacionService service) {
        this.service = service;
    }

    @GetMapping("/resumen")
    public CostoResumenResponse getResumen() {
        return service.calcularResumen();
    }
}
