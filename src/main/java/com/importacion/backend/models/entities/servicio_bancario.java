package com.importacion.backend.models.entities;

import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="servicio_bancario")
public class servicio_bancario {

	@Id
	@GeneratedValue(strategy= GenerationType.IDENTITY )
	@Basic(optional=false)
	
	@Column(name="idbanco")
	
	private Integer idbanco;
	
	@Column(name="ISD")
	private Integer ISD;
	
	
	@Column(name="comision_banco")
	private Integer comision_banco;


	public servicio_bancario() {
		super();
	}


	public servicio_bancario(Integer idbanco) {
		super();
		this.idbanco = idbanco;
	}


	public Integer getIdbanco() {
		return idbanco;
	}


	public void setIdbanco(Integer idbanco) {
		this.idbanco = idbanco;
	}


	public Integer getISD() {
		return ISD;
	}


	public void setISD(Integer iSD) {
		ISD = iSD;
	}


	public Integer getComision_banco() {
		return comision_banco;
	}


	public void setComision_banco(Integer comision_banco) {
		this.comision_banco = comision_banco;
	}
	
	
	
}

