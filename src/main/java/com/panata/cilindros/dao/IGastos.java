package com.panata.cilindros.dao;

import java.sql.Date;
import java.util.Calendar;
import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import com.panata.cilindros.entity.Gastos;

public interface IGastos extends CrudRepository<Gastos, Integer>{

	//Buscar por el dia actual 
	@Query(value="select * from Gastos where Fecha = ?1 ORDER BY fk_categoria" , nativeQuery = true)
	public List<Gastos> findByFecha(String  fecha);
	
	//sumatoria de gastos de fecha inicial hasta fecha final 
	@Query(value="select SUM(cantidad) from Gastos where Fecha  BETWEEN ?1 AND  ?2" , nativeQuery = true)
	public Float sumatoriaMensualGastos(String  fe_inicial, String  fe_final);
	
	//sumatoria de gastos por dia 
	@Query(value="select SUM(cantidad) from Gastos where Fecha = ?1" , nativeQuery = true)
	public Float sumatoriaDiaGastos(String  fe_dia );
}
