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
@Table(name = "diario")
public class diario implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Basic(optional = false)
	@Column(name = "pk_diario")
	private Integer idDiario;

	@Column(name = "fecha_diario")
	@Temporal(TemporalType.DATE)
	@DateTimeFormat(pattern = "yyyy-MM-dd")
	private Calendar fecha_diario;

	// Bodegas
	@Column(name = "fc_cantidad")
	private int fc_cantidad;

	@Column(name = "fc_llenos")
	private int fc_llenos;

	@Column(name = "hg_cantidad")
	private int hg_cantidad;

	@Column(name = "hg_llenos")
	private int hg_llenos;

	@Column(name = "gd_cantidad")
	private int gd_cantidad;

	@Column(name = "gd_llenos")
	private int gd_llenos;

	@Column(name = "lago_cantidad")
	private int lago_cantidad;

	@Column(name = "lago_llenos")
	private int lago_llenos;

	@Column(name = "farfan_cantidad")
	private int farfan_cantidad;

	@Column(name = "farfan_llenos")
	private int farfan_llenos;

	@Column(name = "inoperativos_cantidad")
	private int inoperativos_cantidad;

	@Column(name = "industriales_cantidad")
	private int industriales_cantidad;
	
	@Column(name = "industriales_llenos")
	private int industriales_llenos;
	
	@Column(name = "prestados_cant")
	private int prestados_cantidad;

	@Column(name = "prestados_personas")
	private String prestados_personas;
	

	@Column(name = "prestadoseven_cant")
	private int prestadoseven_cantidad;

	@Column(name = "prestadoseven_personas")
	private String prestadoseven_personas;

	
	@Column(name = "pedidoseven_cant")
	private int pedidoseven_cantidad;

	@Column(name = "pedidoseven_personas")
	private String pedidoseven_personas;


	@Column(name = "adicional1_nombre")
	private String adicional1_nombre;

	@Column(name = "adicional1_cant")
	private int adicional1_cant;

	@Column(name = "adicional2_nombre")
	private String adicional2_nombre;

	@Column(name = "adicional2_cant")
	private int adicional2_cant;

	@Column(name = "adicional3_nombre")
	private String adicional3_nombre;

	@Column(name = "adicional3_cant")
	private int adicional3_cant;


	
	
	
	
	
	
	
	
	
	// duenos de los tanques
	@Column(name = "rosita_cant")
	private int rosita_cantidad;

	@Column(name = "rosita_observacion")
	private String rosita_observacion;

	@Column(name = "leoncio_cant")
	private int leoncio_cantidad;

	@Column(name = "leoncio_observacion")
	private String leoncio_observacion;

	@Column(name = "leonardo_cant")
	private int leonardo_cantidad;

	@Column(name = "leonardo_observacion")
	private String leonordo_observacion;

	@Column(name = "jaramillo_cant")//se pusieron como adicionales
	private int jaramillo_cantidad;

	@Column(name = "jaramillo_observacion")//se pusieron como adicionales
	private String jaramillo_observacion;

	@Column(name = "silvia_cant")//se pusieron como adicionales
	private int silvia_cantidad;

	@Column(name = "silvia_observacion")//se pusieron como adicionales
	private String silvia_observacion;

	
	
	//notas
	@Column(name = "nota1")//se pusieron como adicionales
	private String nota1;

	@Column(name = "nota2")//se pusieron como adicionales
	private String nota2;

	@Column(name = "nota3")//se pusieron como adicionales
	private String nota3;

	
	
	
	
	public diario(Integer idDiario) {
		super();
		this.idDiario = idDiario;
	}

	public diario() {
		super();
	}

	public Integer getIdDiario() {
		return idDiario;
	}

	public void setIdDiario(Integer idDiario) {
		this.idDiario = idDiario;
	}

	public Calendar getFecha_diario() {
		return fecha_diario;
	}

	public void setFecha_diario(Calendar fecha_diario) {
		this.fecha_diario = fecha_diario;
	}

	public int getFc_cantidad() {
		return fc_cantidad;
	}

	public void setFc_cantidad(int fc_cantidad) {
		this.fc_cantidad = fc_cantidad;
	}

	public int getFc_llenos() {
		return fc_llenos;
	}

	public void setFc_llenos(int fc_llenos) {
		this.fc_llenos = fc_llenos;
	}

	public int getHg_cantidad() {
		return hg_cantidad;
	}

	public void setHg_cantidad(int hg_cantidad) {
		this.hg_cantidad = hg_cantidad;
	}

	public int getHg_llenos() {
		return hg_llenos;
	}

	public void setHg_llenos(int hg_llenos) {
		this.hg_llenos = hg_llenos;
	}

	public int getGd_cantidad() {
		return gd_cantidad;
	}

	public void setGd_cantidad(int gd_cantidad) {
		this.gd_cantidad = gd_cantidad;
	}

	public int getGd_llenos() {
		return gd_llenos;
	}

	public void setGd_llenos(int gd_llenos) {
		this.gd_llenos = gd_llenos;
	}

	public int getLago_cantidad() {
		return lago_cantidad;
	}

	public void setLago_cantidad(int lago_cantidad) {
		this.lago_cantidad = lago_cantidad;
	}

	public int getLago_llenos() {
		return lago_llenos;
	}

	public void setLago_llenos(int lago_llenos) {
		this.lago_llenos = lago_llenos;
	}

	public int getFarfan_cantidad() {
		return farfan_cantidad;
	}

	public void setFarfan_cantidad(int farfan_cantidad) {
		this.farfan_cantidad = farfan_cantidad;
	}

	public int getFarfan_llenos() {
		return farfan_llenos;
	}

	public void setFarfan_llenos(int farfan_llenos) {
		this.farfan_llenos = farfan_llenos;
	}

	public int getPrestados_cantidad() {
		return prestados_cantidad;
	}

	public void setPrestados_cantidad(int prestados_cantidad) {
		this.prestados_cantidad = prestados_cantidad;
	}

	public int getRosita_cantidad() {
		return rosita_cantidad;
	}

	public void setRosita_cantidad(int rosita_cantidad) {
		this.rosita_cantidad = rosita_cantidad;
	}

	public String getRosita_observacion() {
		return rosita_observacion;
	}

	public void setRosita_observacion(String rosita_observacion) {
		this.rosita_observacion = rosita_observacion;
	}

	public int getLeoncio_cantidad() {
		return leoncio_cantidad;
	}

	public void setLeoncio_cantidad(int leoncio_cantidad) {
		this.leoncio_cantidad = leoncio_cantidad;
	}

	public String getLeoncio_observacion() {
		return leoncio_observacion;
	}

	public void setLeoncio_observacion(String leoncio_observacion) {
		this.leoncio_observacion = leoncio_observacion;
	}

	public int getLeonardo_cantidad() {
		return leonardo_cantidad;
	}

	public void setLeonardo_cantidad(int leonardo_cantidad) {
		this.leonardo_cantidad = leonardo_cantidad;
	}

	public String getLeonordo_observacion() {
		return leonordo_observacion;
	}

	public void setLeonordo_observacion(String leonordo_observacion) {
		this.leonordo_observacion = leonordo_observacion;
	}

	public int getJaramillo_cantidad() {
		return jaramillo_cantidad;
	}

	public void setJaramillo_cantidad(int jaramillo_cantidad) {
		this.jaramillo_cantidad = jaramillo_cantidad;
	}

	public String getJaramillo_observacion() {
		return jaramillo_observacion;
	}

	public void setJaramillo_observacion(String jaramillo_observacion) {
		this.jaramillo_observacion = jaramillo_observacion;
	}

	public int getSilvia_cantidad() {
		return silvia_cantidad;
	}

	public void setSilvia_cantidad(int silvia_cantidad) {
		this.silvia_cantidad = silvia_cantidad;
	}

	public String getSilvia_observacion() {
		return silvia_observacion;
	}

	public void setSilvia_observacion(String silvia_observacion) {
		this.silvia_observacion = silvia_observacion;
	}

	
	
	public int getInoperativos_cantidad() {
		return inoperativos_cantidad;
	}

	public void setInoperativos_cantidad(int inoperativos_cantidad) {
		this.inoperativos_cantidad = inoperativos_cantidad;
	}

	public int getIndustriales_cantidad() {
		return industriales_cantidad;
	}

	public void setIndustriales_cantidad(int industriales_cantidad) {
		this.industriales_cantidad = industriales_cantidad;
	}

	public int getIndustriales_llenos() {
		return industriales_llenos;
	}

	public void setIndustriales_llenos(int industriales_llenos) {
		this.industriales_llenos = industriales_llenos;
	}

	
	
	
	


	public int getPrestadoseven_cantidad() {
		return prestadoseven_cantidad;
	}

	public void setPrestadoseven_cantidad(int prestadoseven_cantidad) {
		this.prestadoseven_cantidad = prestadoseven_cantidad;
	}



	public int getPedidoseven_cantidad() {
		return pedidoseven_cantidad;
	}

	public void setPedidoseven_cantidad(int pedidoseven_cantidad) {
		this.pedidoseven_cantidad = pedidoseven_cantidad;
	}



	public String getPrestados_personas() {
		return prestados_personas;
	}

	public void setPrestados_personas(String prestados_personas) {
		this.prestados_personas = prestados_personas;
	}

	public String getPrestadoseven_personas() {
		return prestadoseven_personas;
	}

	public void setPrestadoseven_personas(String prestadoseven_personas) {
		this.prestadoseven_personas = prestadoseven_personas;
	}

	public String getPedidoseven_personas() {
		return pedidoseven_personas;
	}

	public void setPedidoseven_personas(String pedidoseven_personas) {
		this.pedidoseven_personas = pedidoseven_personas;
	}

	
	
	
	
	
	
	
	
	
	public String getAdicional1_nombre() {
		return adicional1_nombre;
	}

	public void setAdicional1_nombre(String adicional1_nombre) {
		this.adicional1_nombre = adicional1_nombre;
	}

	public int getAdicional1_cant() {
		return adicional1_cant;
	}

	public void setAdicional1_cant(int adicional1_cant) {
		this.adicional1_cant = adicional1_cant;
	}

	public String getAdicional2_nombre() {
		return adicional2_nombre;
	}

	public void setAdicional2_nombre(String adicional2_nombre) {
		this.adicional2_nombre = adicional2_nombre;
	}

	public int getAdicional2_cant() {
		return adicional2_cant;
	}

	public void setAdicional2_cant(int adicional2_cant) {
		this.adicional2_cant = adicional2_cant;
	}

	public String getAdicional3_nombre() {
		return adicional3_nombre;
	}

	public void setAdicional3_nombre(String adicional3_nombre) {
		this.adicional3_nombre = adicional3_nombre;
	}

	public int getAdicional3_cant() {
		return adicional3_cant;
	}

	public void setAdicional3_cant(int adicional3_cant) {
		this.adicional3_cant = adicional3_cant;
	}
	
	
	
	
	
	
	
	

	public String getNota1() {
		return nota1;
	}

	public void setNota1(String nota1) {
		this.nota1 = nota1;
	}

	public String getNota2() {
		return nota2;
	}

	public void setNota2(String nota2) {
		this.nota2 = nota2;
	}

	public String getNota3() {
		return nota3;
	}

	public void setNota3(String nota3) {
		this.nota3 = nota3;
	}

	public String fechaDiario() {

		if (this.fecha_diario == null)
			return "-";
		SimpleDateFormat sdf = new SimpleDateFormat("dd/MMM/yyyy");

		return sdf.format(this.fecha_diario.getTime());
	}

	public int TotalUbicacion (){

		

		return this.adicional3_cant+this.adicional2_cant+this.adicional1_cant+this.inoperativos_cantidad+this.industriales_cantidad-this.pedidoseven_cantidad+this.prestadoseven_cantidad+this.farfan_cantidad+this.fc_cantidad+this.gd_cantidad+this.hg_cantidad+this.lago_cantidad+this.prestados_cantidad;
	}
	
	public int TotalDuenos (){


		return this.leonardo_cantidad+this.leoncio_cantidad+this.rosita_cantidad+this.jaramillo_cantidad+this.silvia_cantidad;
	}
	
	public int resta(int a, int b) {
		
		return a-b;
	}
	
	
	public int TotalUbicacionllenos (){

		return this.farfan_llenos+this.fc_llenos+this.gd_llenos+this.hg_llenos+this.lago_llenos+this.industriales_llenos;
	}

}
