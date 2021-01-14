package com.panata.cilindros.service;

import java.util.Calendar;
import java.util.List;

import com.panata.cilindros.entity.diario;



public interface IDiarioService {
	
	public void save(diario a);
	public diario findById(Integer id);
	public void delete(Integer id);
	public List<diario> findAll();
	
	public Integer ultimoRegistro();

}
