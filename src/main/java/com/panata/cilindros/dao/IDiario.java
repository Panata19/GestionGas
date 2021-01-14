package com.panata.cilindros.dao;

import java.util.Calendar;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import com.panata.cilindros.entity.diario;


public interface IDiario extends CrudRepository<diario, Integer>{

	
	@Query(value="SELECT MAX(pk_diario) FROM diario" , nativeQuery = true)
	public Integer ultimoRegistro();
}
