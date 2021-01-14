package com.panata.cilindros.entity;

import java.io.Serializable;
import java.text.SimpleDateFormat;
import java.util.Calendar;

import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import org.springframework.format.annotation.DateTimeFormat;

@Entity
@Table(name = "prestados")
public class prestados implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Basic(optional = false)
	@Column(name = "pk_prestados")
	private Integer idPrestados;

	@Column(name = "fecha_prestado")
	@Temporal(TemporalType.DATE)
	@DateTimeFormat(pattern = "yyyy-MM-dd")
	private Calendar fecha_prestado;

	@Column(name = "fecha_devuelto")
	@Temporal(TemporalType.DATE)
	@DateTimeFormat(pattern = "yyyy-MM-dd")
	private Calendar fecha_devuelto;

	@Column(name = "cantidad")
	private int cantidad;

	@Column(name = "observaciones")
	private String observaciones;

	@Column(name = "tipo") // cilindro 1 de 15 kg y 2 de 45 kg
	private int tipo;

	@Column(name = "activo") // 1 prestado 2 devuelto
	private int activo;

	@Column(name = "responsable")
	private String responsable;

	public String getResponsable() {
		return responsable;
	}

	public void setResponsable(String responsable) {
		this.responsable = responsable;
	}

	public prestados() {
		super();
	}

	public prestados(Integer idPrestados) {
		super();
		this.idPrestados = idPrestados;
	}

	public Integer getIdPrestados() {
		return idPrestados;
	}

	public void setIdPrestados(Integer idPrestados) {
		this.idPrestados = idPrestados;
	}

	public Calendar getFecha_prestado() {
		return fecha_prestado;
	}

	public void setFecha_prestado(Calendar fecha_prestado) {
		this.fecha_prestado = fecha_prestado;
	}

	public int getCantidad() {
		return cantidad;
	}

	public void setCantidad(int cantidad) {
		this.cantidad = cantidad;
	}

	public String getObservaciones() {
		return observaciones;
	}

	public void setObservaciones(String observaciones) {
		this.observaciones = observaciones;
	}

	public int getTipo() {
		return tipo;
	}

	public void setTipo(int tipo) {
		this.tipo = tipo;
	}

	public int getActivo() {
		return activo;
	}

	public void setActivo(int activo) {
		this.activo = activo;
	}

	// Fecha prestado

	public String fechaPrestado() {

		if (this.fecha_prestado == null)
			return "-";
		SimpleDateFormat sdf = new SimpleDateFormat("dd/MMM/yyyy");
		return sdf.format(this.fecha_prestado.getTime());

	}

	public String fechaDevuelto() {

		if (this.fecha_devuelto == null)
			return "-";
		SimpleDateFormat sdf = new SimpleDateFormat("dd/MMM/yyyy");
		return sdf.format(this.fecha_devuelto.getTime());

	}

	public Calendar getFecha_devuelto() {
		return fecha_devuelto;
	}

	public void setFecha_devuelto(Calendar fecha_devuelto) {
		this.fecha_devuelto = fecha_devuelto;
	}

}
