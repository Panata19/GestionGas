package com.panata.cilindros.entity;

import java.io.Serializable;
import java.sql.Date;
import java.text.SimpleDateFormat;
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
import javax.validation.constraints.DecimalMin;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import org.springframework.data.annotation.Transient;
import org.springframework.format.annotation.DateTimeFormat;

@Entity
@Table(name = "gastos")
public class Gastos implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Basic(optional = false)
	@Column(name = "pk_gastos")
	private Integer idGastos;
	
	@Column(name = "nombre")
	@NotEmpty
	@Size(max=60)
	private String Nombre;
	
	@DateTimeFormat(pattern = "yyyy-MM-dd")	
	@Column(name = "Fecha")
	
	private Calendar Fecha;
	
	@Column(name = "cantidad")
	@Min(1)
	//@DecimalMin("1.00") 
	private Float Cantidad;
	
	@JoinColumn(name="fk_categoria", referencedColumnName="pk_categoria")
	@ManyToOne
	private Categoria categoria;
	
	@Transient
	private Integer idCategoria;

	public Integer getIdCategoria() {
		return idCategoria;
	}

	public void setIdCategoria(Integer idCategoria) {
		this.idCategoria = idCategoria;
	}

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
	
	
	public String fechaFormat() {
		SimpleDateFormat sdf = new SimpleDateFormat("dd/MMM/yyyy");		
		return sdf.format(this.Fecha.getTime());
	}

}
