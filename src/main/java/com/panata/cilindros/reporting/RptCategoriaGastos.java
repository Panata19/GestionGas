package com.panata.cilindros.reporting;

import java.io.Serializable;

public class RptCategoriaGastos implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private String categoria ;
	private float gasto;
	
	public RptCategoriaGastos() {
		super();
		// TODO Auto-generated constructor stub
	}

	public String getCategoria() {
		return categoria;
	}

	public void setCategoria(String categoria) {
		this.categoria = categoria;
	}

	public float getGasto() {
		return gasto;
	}

	public void setGasto(float gasto) {
		this.gasto = gasto;
	}
	
	

}
