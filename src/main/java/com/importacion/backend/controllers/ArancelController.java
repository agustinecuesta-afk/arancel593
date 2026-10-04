package com.importacion.backend.controllers;

import com.importacion.backend.dto.SubpartidaAduana;
import com.importacion.backend.services.SenaeArancelService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/arancel")
public class ArancelController {

    private final SenaeArancelService service;

    public ArancelController(SenaeArancelService service) {
        this.service = service;
    }

    @GetMapping("/subpartidas")
    public List<SubpartidaAduana> buscarSubpartidas(
            @RequestParam(required = false) String codigo,
            @RequestParam(required = false) String descripcion) {
        return service.buscar(codigo, descripcion);
    }
}
