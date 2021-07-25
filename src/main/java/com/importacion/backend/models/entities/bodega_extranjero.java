package com.importacion.backend.models.entities;



import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="bodega_extranjero")

public class bodega_extranjero { //POJO
	
	
	@Id
	@GeneratedValue(strategy= GenerationType.IDENTITY )
	@Basic(optional=false)
	
	
	@Column(name="id_bodega")
	private Integer idbodega;
	
	@Column(name="pais")
	private String pais;
	
	@Column(name="nombre")
	private String nombre;
	
	@Column(name="entrada")
	private float entrada;
	
	@Column(name="etiqueta")
	private float etiqueta;
	
	@Column(name="manejo")
	private float manejo;
	
	@Column(name="salida")
	private float salida;
	
	
	
	public bodega_extranjero() {
		super();
	}


	public bodega_extranjero(Integer idbodega) {
		super();
		this.idbodega = idbodega;
	}


	public Integer getIdbodega() {
		return idbodega;
	}


	public void setIdbodega(Integer idbodega) {
		this.idbodega = idbodega;
	}


	public String getPais() {
		return pais;
	}


	public void setPais(String pais) {
		this.pais = pais;
	}


	public String getNombre() {
		return nombre;
	}


	public void setNombre(String nombre) {
		this.nombre = nombre;
	}


	public float getEntrada() {
		return entrada;
	}


	public void setEntrada(float entrada) {
		this.entrada = entrada;
	}


	public float getEtiqueta() {
		return etiqueta;
	}


	public void setEtiqueta(float etiqueta) {
		this.etiqueta = etiqueta;
	}


	public float getManejo() {
		return manejo;
	}


	public void setManejo(float manejo) {
		this.manejo = manejo;
	}


	public float getSalida() {
		return salida;
	}


	public void setSalida(float salida) {
		this.salida = salida;
	}
	
	
	

}

