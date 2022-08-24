package com.ingenieriaweb.springboot.web.app.models;

import java.io.Serializable;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;

@Entity
@Table(name = "EMPLEADO")
public class EMPLEADO implements Serializable{

	private static final long serialVersionUID = 1L;
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long idEmpleado;
	
	@OneToOne
	@JoinColumn(name="idEmpleado", referencedColumnName = "id") //name="nombre de la clave primaria de la tabla local", referencedColumnName="hace referencia a la columna de la tabla destino"
	private USUARIO usuario;
	
	@ManyToOne
	@JoinColumn(name = "idSucursal")
	private SUCURSAL sucursal;

	public Long getIdEmpleado() {
		return idEmpleado;
	}

	public void setIdEmpleado(Long idEmpleado) {
		this.idEmpleado = idEmpleado;
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
