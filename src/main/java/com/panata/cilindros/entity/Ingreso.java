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
import javax.validation.constraints.DecimalMin;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;

import org.springframework.format.annotation.DateTimeFormat;

@Entity
@Table(name = "ingreso")
public class Ingreso implements Serializable {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Basic(optional = false)
	@Column(name = "pk_ingreso")
	private Integer idIngreso;
	
	@Column(name = "fecha")
	@DateTimeFormat(pattern = "yyyy-MM-dd")	
	private Calendar Fecha;
	
	@Column(name = "cantidad")
	@Min(1)
	private Float Cantidad;

	public Ingreso() {
		super();
		// TODO Auto-generated constructor stub
	}
	
	
	public Ingreso(Integer idIngreso) {
		super();
		this.idIngreso = idIngreso;
		// TODO Auto-generated constructor stub
	}


	public Integer getIdIngreso() {
		return idIngreso;
	}


	public void setIdIngreso(Integer idIngreso) {
		this.idIngreso = idIngreso;
	}


	public Calendar getFecha() {
		return Fecha;
	}


	public void setFecha(Calendar fecha) {
		Fecha = fecha;
	}


	public Float getCantidad() {
		return Cantidad;
	}


	public void setCantidad(Float cantidad) {
		Cantidad = cantidad;
	}
	
	
	public String fechaFormat() {
		SimpleDateFormat sdf = new SimpleDateFormat("dd/MMM/yyyy");		
		return sdf.format(this.Fecha.getTime());
	}
	
	

}
