package com.importacion.backend.models.entities;

import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="organismo_control")
public class organismo_control {
	
	
	@Id
	@GeneratedValue(strategy= GenerationType.IDENTITY )
	@Basic(optional=false)
	
	@Column(name="idorganismo")

	private Integer idorganismo;
	
	@Column(name="tipo_organismo")
	private String tipo_organismo;

	public organismo_control() {
		super();
	}

	public organismo_control(Integer idorganismo) {
		super();
		this.idorganismo = idorganismo;
	}

	public Integer getIdorganismo() {
		return idorganismo;
	}

	public void setIdorganismo(Integer idorganismo) {
		this.idorganismo = idorganismo;
	}

	public String getTipo_organismo() {
		return tipo_organismo;
	}

	public void setTipo_organismo(String tipo_organismo) {
		this.tipo_organismo = tipo_organismo;
	}
	
	
	
}
