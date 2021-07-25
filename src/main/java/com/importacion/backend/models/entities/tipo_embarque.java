package com.importacion.backend.models.entities;

import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;


@Entity
@Table(name="idembarque")

public class tipo_embarque {
	
	@Id
	@GeneratedValue(strategy= GenerationType.IDENTITY )
	@Basic(optional=false)
	
	
	@Column(name="idembarque")
	private Integer idembarque;
	
	

	public tipo_embarque() {
		super();
	}



	public tipo_embarque(Integer idembarque) {
		super();
		this.idembarque = idembarque;
	}



	public Integer getIdembarque() {
		return idembarque;
	}



	public void setIdembarque(Integer idembarque) {
		this.idembarque = idembarque;
	}
	
	

}