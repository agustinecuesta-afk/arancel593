package com.importacion.backend.models.entities;

import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "traslado_carga")
public class traslado_carga {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "idtraslado")
    private Integer idtraslado;

    @Column(name = "flete_camion")
    private Integer flete_camion;

    @Column(name = "guardia_armada")
    private Integer guardia_armada;

    @Column(name = "estibadores")
    private Integer estibadores;

    public traslado_carga() {
        super();
    }

    public traslado_carga(Integer idtraslado) {
        super();
        this.idtraslado = idtraslado;
    }

    public Integer getIdtraslado() {
        return idtraslado;
    }

    public void setIdtraslado(Integer idtraslado) {
        this.idtraslado = idtraslado;
    }

    public Integer getFlete_camion() {
        return flete_camion;
    }

    public void setFlete_camion(Integer flete_camion) {
        this.flete_camion = flete_camion;
    }

    public Integer getGuardia_armada() {
        return guardia_armada;
    }

    public void setGuardia_armada(Integer guardia_armada) {
        this.guardia_armada = guardia_armada;
    }

    public Integer getEstibadores() {
        return estibadores;
    }

    public void setEstibadores(Integer estibadores) {
        this.estibadores = estibadores;
    }
}

