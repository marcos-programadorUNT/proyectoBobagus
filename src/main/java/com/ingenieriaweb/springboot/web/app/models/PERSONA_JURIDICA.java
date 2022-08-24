package com.ingenieriaweb.springboot.web.app.models;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.validation.constraints.NotEmpty;

@Entity
@Table(name = "PERSONA_JURIDICA")
public class PERSONA_JURIDICA implements Serializable{
	
	private static final long serialVersionUID = 1L;
	@Id
	private Long idCliente;
	
	@OneToOne
	@JoinColumn(name="idCliente", referencedColumnName = "idCliente") //name="nombre de la clave primaria de la tabla local", referencedColumnName="hace referencia a la columna de la tabla destino"
	private CLIENTE cliente;
	
	@NotEmpty
	private String RUC;
	@NotEmpty
	private String razonSocial;
	@NotEmpty
	private String direccion;
	public Long getIdCliente() {
		return idCliente;
	}
	public void setIdCliente(Long idCliente) {
		this.idCliente = idCliente;
	}
	public CLIENTE getCliente() {
		return cliente;
	}
	public void setCliente(CLIENTE cliente) {
		this.cliente = cliente;
	}
	public String getRUC() {
		return RUC;
	}
	public void setRUC(String rUC) {
		RUC = rUC;
	}
	public String getRazonSocial() {
		return razonSocial;
	}
	public void setRazonSocial(String razonSocial) {
		this.razonSocial = razonSocial;
	}
	public String getDireccion() {
		return direccion;
	}
	public void setDireccion(String direccion) {
		this.direccion = direccion;
	}
	public static long getSerialversionuid() {
		return serialVersionUID;
	}
	
	

}
