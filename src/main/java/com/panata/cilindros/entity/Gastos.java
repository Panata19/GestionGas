package com.panata.cilindros.entity;

import java.io.Serializable;
import java.util.Calendar;

import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name = "gastos")
public class Gastos implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Basic(optional = false)
	@Column(name = "pk_gastos")
	private Integer idGastos;
	
	@Column(name = "nombre")
	private String Nombre;
	
	@Column(name = "fecha")
	private Calendar Fecha;
	
	@Column(name = "cantidad")
	private Float Cantidad;
	
	@JoinColumn(name="fk_categoria", referencedColumnName="pk_categoria")
	@ManyToOne
	private Categoria categoria;

	public Gastos() {
		super();
		// TODO Auto-generated constructor stub
	}
	
	public Gastos(Integer idGastos) {
		super();
		this.idGastos = idGastos;
		// TODO Auto-generated constructor stub
	}

	public Integer getIdGastos() {
		return idGastos;
	}

	public void setIdGastos(Integer idGastos) {
		this.idGastos = idGastos;
	}

	public String getNombre() {
		return Nombre;
	}

	public void setNombre(String nombre) {
		Nombre = nombre;
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

	public Categoria getCategoria() {
		return categoria;
	}

	public void setCategoria(Categoria categoria) {
		this.categoria = categoria;
	}
	
	
	
	
	
}
