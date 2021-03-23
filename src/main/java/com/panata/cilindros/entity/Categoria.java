package com.panata.cilindros.entity;

import java.io.Serializable;
import java.util.List;

import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;

import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
@Table(name = "categoria")
public class Categoria implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Basic(optional = false)
	@Column(name = "pk_categoria")
	private Integer idCategoria;
	
	@Column(name = "nombre")
	@NotEmpty
	@Size(max=60)
	private String Nombre;
	
	
	@JsonIgnore
	@OneToMany(mappedBy="categoria", fetch=FetchType.LAZY) 
	private List<Gastos> gastos;


	public Categoria() {
		super();
		// TODO Auto-generated constructor stub
	}
	
	public Categoria(Integer idCategoria) {
		super();
		this.idCategoria = idCategoria;
		// TODO Auto-generated constructor stub
	}

	public Integer getIdCategoria() {
		return idCategoria;
	}

	public void setIdCategoria(Integer idCategoria) {
		this.idCategoria = idCategoria;
	}

	public String getNombre() {
		return Nombre;
	}

	public void setNombre(String nombre) {
		Nombre = nombre;
	}

	public List<Gastos> getGastos() {
		return gastos;
	}

	public void setGastos(List<Gastos> gastos) {
		this.gastos = gastos;
	}
	
	
	
	

}
