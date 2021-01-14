package com.panata.cilindros.dao;

import java.util.Calendar;
import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import com.panata.cilindros.entity.eventuales;

public interface IEventuales extends CrudRepository <eventuales, Integer>{

	@Query(value="SELECT * FROM eventual WHERE tipo = ?1 " , nativeQuery = true)
	public List<eventuales> findBytipo(int tipo);
	
	//Suma pedidos
	@Query(value="SELECT SUM(cantidad) FROM eventual WHERE tipo = 1 and estado=1 " , nativeQuery = true)
	public Integer sumaPedidoEventual();


	//suma prestados
	@Query(value="SELECT SUM(cantidad) FROM eventual WHERE tipo = 2 and estado=1 " , nativeQuery = true)
	public Integer restaPrestadoEventual();

	
	@Query(value="SELECT * FROM eventual WHERE tipo = ?1 and estado=1 " , nativeQuery = true)
	public List<eventuales> findBytipoEventuales(int tipo);
	

	//suma prestados
	@Query(value="SELECT SUM(cantidad) FROM eventual WHERE tipo = ?1 and estado=?2 " , nativeQuery = true)
	public Integer TotalEventuales(int tipo, int estado);
}
