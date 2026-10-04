package com.importacion.backend.dto;

import com.fasterxml.jackson.annotation.JsonProperty;

public class SubpartidaAduana {

    private String identif;

    @JsonProperty("codigo_subpartida")
    private String codigoSubpartida;

    @JsonProperty("codigo_complementario")
    private String codigoComplementario;

    @JsonProperty("codigo_suplementario")
    private String codigoSuplementario;

    @JsonProperty("descripcion_elemento")
    private String descripcion;

    @JsonProperty("fecha_inicio_vigencia")
    private String fechaInicioVigencia;

    @JsonProperty("fecha_fin_vigencia")
    private String fechaFinVigencia;

    @JsonProperty("unidades_fisicas")
    private String unidadesFisicas;

    @JsonProperty("comentario_apertura")
    private String comentarioApertura;

    public String getIdentif() {
        return identif;
    }

    public void setIdentif(String identif) {
        this.identif = identif;
    }

    public String getCodigoSubpartida() {
        return codigoSubpartida;
    }

    public void setCodigoSubpartida(String codigoSubpartida) {
        this.codigoSubpartida = codigoSubpartida;
    }

    public String getCodigoComplementario() {
        return codigoComplementario;
    }

    public void setCodigoComplementario(String codigoComplementario) {
        this.codigoComplementario = codigoComplementario;
    }

    public String getCodigoSuplementario() {
        return codigoSuplementario;
    }

    public void setCodigoSuplementario(String codigoSuplementario) {
        this.codigoSuplementario = codigoSuplementario;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public String getFechaInicioVigencia() {
        return fechaInicioVigencia;
    }

    public void setFechaInicioVigencia(String fechaInicioVigencia) {
        this.fechaInicioVigencia = fechaInicioVigencia;
    }

    public String getFechaFinVigencia() {
        return fechaFinVigencia;
    }

    public void setFechaFinVigencia(String fechaFinVigencia) {
        this.fechaFinVigencia = fechaFinVigencia;
    }

    public String getUnidadesFisicas() {
        return unidadesFisicas;
    }

    public void setUnidadesFisicas(String unidadesFisicas) {
        this.unidadesFisicas = unidadesFisicas;
    }

    public String getComentarioApertura() {
        return comentarioApertura;
    }

    public void setComentarioApertura(String comentarioApertura) {
        this.comentarioApertura = comentarioApertura;
    }
}
