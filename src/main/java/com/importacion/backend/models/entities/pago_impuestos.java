package com.importacion.backend.models.entities;

import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="pago_impuestos")
public class pago_impuestos {
	
	
	@Id
	@GeneratedValue(strategy= GenerationType.IDENTITY )
	@Basic(optional=false)
	
	@Column(name="idpago_impuestos")
	
	private Integer idpago_impuestos;
	
	@Column(name="efectivo")
	private Integer efectivo;

	public pago_impuestos() {
		super();
	}

	public pago_impuestos(Integer idpago_impuestos) {
		super();
		this.idpago_impuestos = idpago_impuestos;
	}

	public Integer getIdpago_impuestos() {
		return idpago_impuestos;
	}

	public void setIdpago_impuestos(Integer idpago_impuestos) {
		this.idpago_impuestos = idpago_impuestos;
	}

	public Integer getEfectivo() {
		return efectivo;
	}

	public void setEfectivo(Integer efectivo) {
		this.efectivo = efectivo;
	}
	
	
	

}

