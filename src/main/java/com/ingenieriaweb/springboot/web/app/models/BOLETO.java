package com.ingenieriaweb.springboot.web.app.models;

import java.io.Serializable;
import java.util.Date;
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
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

import org.springframework.format.annotation.DateTimeFormat;

@Entity
@Table(name = "BOLETO")
public class BOLETO implements Serializable{
	
	private static final long serialVersionUID = 1L;
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long idBoleto;
	@NotEmpty
	private String estado;
	@NotNull
	@Temporal(TemporalType.DATE)
	@DateTimeFormat(pattern="yyyy-MM-dd")
	private Date fechaEmision;
	@NotNull
	private double costoAsiento;
	@NotNull
	private double descuentoAsiento;
	@OneToOne
	@JoinColumn(name = "idCronograma")
	private CRONOGRAMA_VIAJE cronograma_viaje;
	@ManyToOne
	@JoinColumn(name = "idSucursal")
	private SUCURSAL sucursal;
	@ManyToOne
	@JoinColumn(name = "idCliente")
	private CLIENTE cliente;
	@ManyToOne
	@JoinColumn(name = "idMedioVenta")
	private MEDIO_VENTA medio_venta;
	@ManyToOne
	@JoinColumn(name = "idAsiento")
	private ASIENTO asiento;
	public Long getIdBoleto() {
		return idBoleto;
	}
	public void setIdBoleto(Long idBoleto) {
		this.idBoleto = idBoleto;
	}
	public String getEstado() {
		return estado;
	}
	public void setEstado(String estado) {
		this.estado = estado;
	}
	public Date getFechaEmision() {
		return fechaEmision;
	}
	public void setFechaEmision(Date fechaEmision) {
		this.fechaEmision = fechaEmision;
	}
	public double getCostoAsiento() {
		return costoAsiento;
	}
	public void setCostoAsiento(double costoAsiento) {
		this.costoAsiento = costoAsiento;
	}
	public double getDescuentoAsiento() {
		return descuentoAsiento;
	}
	public void setDescuentoAsiento(double descuentoAsiento) {
		this.descuentoAsiento = descuentoAsiento;
	}
	public CRONOGRAMA_VIAJE getCronograma_viaje() {
		return cronograma_viaje;
	}
	public void setCronograma_viaje(CRONOGRAMA_VIAJE cronograma_viaje) {
		this.cronograma_viaje = cronograma_viaje;
	}
	public SUCURSAL getSucursal() {
		return sucursal;
	}
	public void setSucursal(SUCURSAL sucursal) {
		this.sucursal = sucursal;
	}
	public CLIENTE getCliente() {
		return cliente;
	}
	public void setCliente(CLIENTE cliente) {
		this.cliente = cliente;
	}
	public MEDIO_VENTA getMedio_venta() {
		return medio_venta;
	}
	public void setMedio_venta(MEDIO_VENTA medio_venta) {
		this.medio_venta = medio_venta;
	}
	public static long getSerialversionuid() {
		return serialVersionUID;
	}
	public ASIENTO getAsiento() {
		return asiento;
	}
	public void setAsiento(ASIENTO asiento) {
		this.asiento = asiento;
	}
	
	

}
