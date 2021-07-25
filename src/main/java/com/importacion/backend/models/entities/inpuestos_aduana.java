package com.importacion.backend.models.entities;

import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="inpuestos_aduana")
public class inpuestos_aduana {
	
	
	@Id
	@GeneratedValue(strategy= GenerationType.IDENTITY )
	@Basic(optional=false)
	
	@Column(name="idaduana")
	private Integer idaduana;
	
	@Column(name="iva")
	
	private Integer iva;
	
	@Column(name="fodinfa")
	private Integer fodinfa;
	
	@Column(name="advaloren")
	private Integer advaloren;
	
	@Column(name="ice")
	private Integer ice;

	public inpuestos_aduana() {
		super();
	}

	public inpuestos_aduana(Integer idaduana) {
		super();
		this.idaduana = idaduana;
	}

	public Integer getIdaduana() {
		return idaduana;
	}

	public void setIdaduana(Integer idaduana) {
		this.idaduana = idaduana;
	}

	public Integer getIva() {
		return iva;
	}

	public void setIva(Integer iva) {
		this.iva = iva;
	}

	public Integer getFodinfa() {
		return fodinfa;
	}

	public void setFodinfa(Integer fodinfa) {
		this.fodinfa = fodinfa;
	}

	public Integer getAdvaloren() {
		return advaloren;
	}

	public void setAdvaloren(Integer advaloren) {
		this.advaloren = advaloren;
	}

	public Integer getIce() {
		return ice;
	}

	public void setIce(Integer ice) {
		this.ice = ice;
	}
	
	

	

}
