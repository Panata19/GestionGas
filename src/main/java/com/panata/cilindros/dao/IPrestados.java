package com.panata.cilindros.dao;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;


import com.panata.cilindros.entity.prestados;

public interface IPrestados extends CrudRepository <prestados, Integer> {


	//Si es tanque de 15 o 45 kg
	@Query(value="SELECT * FROM prestados WHERE tipo = ?1 " , nativeQuery = true)
	public List<prestados> findBytipo(int tipo);
	

	//Si es tanque de 15 o 45 kg
	@Query(value="SELECT SUM(cantidad) FROM prestados WHERE tipo = 1 and activo=1 " , nativeQuery = true)
	public Integer sumaPrestados();
	
	@Query(value="SELECT * FROM prestados WHERE tipo = 1 and activo=1 " , nativeQuery = true)
	public List<prestados> findBytipoPrestados();
	
	
	
		@Query(value="SELECT SUM(cantidad) FROM prestados WHERE tipo = ?1 and activo=?2 " , nativeQuery = true)
		public Integer total(int tipo, int activo);
	
}
