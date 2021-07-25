package com.importacion.backend.models.entities;

import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="gastos_puerto")
public class gastos_puerto {
	
	
	@Id
	@GeneratedValue(strategy= GenerationType.IDENTITY )
	@Basic(optional=false)
	
	@Column(name="idpuerto")
	private Integer idpuerto;
	
	@Column(name="almacenaje")
	private Integer almacenaje;
	
	@Column(name="agente_aduana")
	private Integer agente_aduana;
	
	@Column(name="regimen_especial_agente")
	private Integer regimen_especial_agente;
	
	@Column(name="candado_satelital")
	private Integer candado_satelital;

	public gastos_puerto() {
		super();
	}

	public gastos_puerto(Integer idpuerto) {
		super();
		this.idpuerto = idpuerto;
	}

	public Integer getIdpuerto() {
		return idpuerto;
	}

	public void setIdpuerto(Integer idpuerto) {
		this.idpuerto = idpuerto;
	}

	public Integer getAlmacenaje() {
		return almacenaje;
	}

	public void setAlmacenaje(Integer almacenaje) {
		this.almacenaje = almacenaje;
	}

	public Integer getAgente_aduana() {
		return agente_aduana;
	}

	public void setAgente_aduana(Integer agente_aduana) {
		this.agente_aduana = agente_aduana;
	}

	public Integer getRegimen_especial_agente() {
		return regimen_especial_agente;
	}

	public void setRegimen_especial_agente(Integer regimen_especial_agente) {
		this.regimen_especial_agente = regimen_especial_agente;
	}

	public Integer getCandado_satelital() {
		return candado_satelital;
	}

	public void setCandado_satelital(Integer candado_satelital) {
		this.candado_satelital = candado_satelital;
	}
	
	
	

}
