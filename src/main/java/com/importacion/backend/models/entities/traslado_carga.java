package com.importacion.backend.models.entities;

import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="traslado_carga")
public class traslado_carga {
	
	@Id
	@GeneratedValue(strategy= GenerationType.IDENTITY )
	@Basic(optional=false)
	
	@Column(name="idtraslado")
	
	private Integer idtraslado;
	
	@Column(name="flete_camion")
	private Integer flete_camion;
	
	@Column(name="guardia_armada")
	private Integer guardia_armada;
	
	@Column(name="estibadores")
	private Integer estibadores;
	
	
	

}

