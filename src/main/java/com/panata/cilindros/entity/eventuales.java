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
@Table(name="eventual")
public class eventuales implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Basic(optional= false)
	@Column(name="pk_eventual")
	private Integer ideventual;


	@Column(name = "fecha_peticion")
	@Temporal(TemporalType.DATE)
	@DateTimeFormat(pattern = "yyyy-MM-dd")	
	private Calendar fecha_peticion;
	

	@Column(name = "fecha_devolucion")
	@Temporal(TemporalType.DATE)
	@DateTimeFormat(pattern = "yyyy-MM-dd")	
	private Calendar fecha_devolucion;
	
	@Column(name="responsable")
	private String responsable;
	
	public String getResponsable() {
		return responsable;
	}



	public void setResponsable(String responsable) {
		this.responsable = responsable;
	}

	@Column(name="cantidad")
	private int cantidad;
	
	@Column(name="saldo")
	private int saldo;
	

	@Column(name="tipo") //si es pedido 1 o prestado 2
	private int tipo;
	
	
	@Column(name="observaciones")
	private String observaciones;
	
	

	@Column(name="estado") //1 prestado,  2 devuelto, 3 bono
	private int estado;



	public eventuales() {
		super();
	}



	public eventuales(Integer ideventual) {
		super();
		this.ideventual = ideventual;
	}



	public Integer getIdeventual() {
		return ideventual;
	}



	public void setIdeventual(Integer ideventual) {
		this.ideventual = ideventual;
	}



	public Calendar getFecha_peticion() {
		return fecha_peticion;
	}



	public void setFecha_peticion(Calendar fecha_peticion) {
		this.fecha_peticion = fecha_peticion;
	}



	public Calendar getFecha_devolucion() {
		return fecha_devolucion;
	}



	public void setFecha_devolucion(Calendar fecha_devolucion) {
		this.fecha_devolucion = fecha_devolucion;
	}






	public int getCantidad() {
		return cantidad;
	}



	public void setCantidad(int cantidad) {
		this.cantidad = cantidad;
	}



	public int getTipo() {
		return tipo;
	}



	public void setTipo(int tipo) {
		this.tipo = tipo;
	}



	public String getObservaciones() {
		return observaciones;
	}



	public void setObservaciones(String observaciones) {
		this.observaciones = observaciones;
	}



	public int getEstado() {
		return estado;
	}



	public void setEstado(int estado) {
		this.estado = estado;
	}
	
	
	



	public int getSaldo() {
		return saldo;
	}



	public void setSaldo(int saldo) {
		this.saldo = saldo;
	}



	public String fechaPeticion() {
		if(this.fecha_peticion == null ) return "-";
		SimpleDateFormat sdf = new SimpleDateFormat("dd/MMM/yyyy");
		
		return sdf.format(this.fecha_peticion.getTime());
	}
	
	public String fechaDevolucion() {
		if(this.fecha_devolucion == null ) return "-";
		SimpleDateFormat sdf = new SimpleDateFormat("dd/MMM/yyyy");
		return sdf.format(this.fecha_devolucion.getTime());
	}
	
	
	
}
