package com.ingenieriaweb.springboot.web.app.models;

import java.io.Serializable;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.validation.constraints.NotEmpty;

@Entity
@Table(name = "CLIENTE")
public class CLIENTE implements Serializable{
	
	private static final long serialVersionUID = 1L;
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long idCliente;
	
	@OneToOne
	@JoinColumn(name = "idCliente")
	private PERSONA_NATURAL persona_natural;
	
	@OneToMany
	@JoinColumn(name = "idCliente")
	private List<BOLETO> boletos;

	public Long getIdCliente() {
		return idCliente;
	}

	public void setIdCliente(Long idCliente) {
		this.idCliente = idCliente;
	}

	public PERSONA_NATURAL getPersona_natural() {
		return persona_natural;
	}

	public void setPersona_natural(PERSONA_NATURAL persona_natural) {
		this.persona_natural = persona_natural;
	}

	public List<BOLETO> getBoletos() {
		return boletos;
	}

	public void setBoletos(List<BOLETO> boletos) {
		this.boletos = boletos;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}


	
	

}
