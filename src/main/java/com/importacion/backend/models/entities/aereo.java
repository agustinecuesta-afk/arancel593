package com.importacion.backend.models.entities;


import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="aereo")
public class aereo {

	
	@Id
	@GeneratedValue(strategy= GenerationType.IDENTITY )
	@Basic(optional=false)
	
	@Column(name="idaereo")
	private Integer  idaereo;
	
	@Column(name="peso")
	private Integer peso;
	
	@Column(name="precio")
	private Integer precio;
	
	

	public aereo() {
		super();
	}

	public aereo(Integer idaereo) {
		super();
		this.idaereo = idaereo;
	}

	public Integer getIdaereo() {
		return idaereo;
	}

	public void setIdaereo(Integer idaereo) {
		this.idaereo = idaereo;
	}

	public Integer getPeso() {
		return peso;
	}

	public void setPeso(Integer peso) {
		this.peso = peso;
	}

	public Integer getPrecio() {
		return precio;
	}

	public void setPrecio(Integer precio) {
		this.precio = precio;
	}
	
	
	
	
}

