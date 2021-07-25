package com.importacion.backend.models.entities;

import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="maritimo")
public class maritimo {
	
	@Id
	@GeneratedValue(strategy= GenerationType.IDENTITY )
	@Basic(optional=false)
	
	@Column(name="idmaritimo")
	
	private Integer idmaritimo;
	
	
	@Column(name="cubicaje")
	
	private Integer cubicaje;
	
	@Column(name="precio")
	private Integer precio;

	public maritimo() {
		super();
	}

	public maritimo(Integer idmaritimo) {
		super();
		this.idmaritimo = idmaritimo;
	}

	public Integer getIdmaritimo() {
		return idmaritimo;
	}

	public void setIdmaritimo(Integer idmaritimo) {
		this.idmaritimo = idmaritimo;
	}

	public Integer getCubicaje() {
		return cubicaje;
	}

	public void setCubicaje(Integer cubicaje) {
		this.cubicaje = cubicaje;
	}

	public Integer getPrecio() {
		return precio;
	}

	public void setPrecio(Integer precio) {
		this.precio = precio;
	}


	
	
	
	
}
