package com.ingenieriaweb.springboot.web.app.models;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.persistence.Table;

@Entity
@Table(name = "ADMINISTRADOR")
public class ADMINISTRADOR implements Serializable{

	private static final long serialVersionUID = 1L;
	@Id
	private Long idAdministrador;
	
	@OneToOne
	@JoinColumn(name="idAdministrador", referencedColumnName = "id") //name="nombre de la clave primaria de la tabla local", referencedColumnName="hace referencia a la columna de la tabla destino"
	private USUARIO usuario;
	
	@ManyToOne
	@JoinColumn(name = "idSucursal")
	private SUCURSAL sucursal;

	public Long getIdAdministrador() {
		return idAdministrador;
	}

	public void setIdAdministrador(Long idAdministrador) {
		this.idAdministrador = idAdministrador;
	}

	public USUARIO getUsuario() {
		return usuario;
	}

	public void setUsuario(USUARIO usuario) {
		this.usuario = usuario;
	}

	public SUCURSAL getSucursal() {
		return sucursal;
	}

	public void setSucursal(SUCURSAL sucursal) {
		this.sucursal = sucursal;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}

	
	

	
	
	
	
	
}
