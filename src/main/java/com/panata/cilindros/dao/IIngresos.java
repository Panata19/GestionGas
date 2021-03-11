package com.panata.cilindros.dao;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import com.panata.cilindros.entity.Ingreso;

public interface IIngresos extends CrudRepository<Ingreso, Integer> {

	@Query(value="select SUM(cantidad) from ingreso where fecha  BETWEEN ?1 AND  ?2" , nativeQuery = true)
	public Float sumatoriaMensualIngreso(String  fe_inicial, String  fe_final);
	
	
	@Query(value="select SUM(cantidad) from ingreso where fecha = ?1 " , nativeQuery = true)
	public Float sumatoriaDiaIngreso(String  fe_dia);
}
