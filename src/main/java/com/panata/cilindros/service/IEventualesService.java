package com.panata.cilindros.service;

import java.util.List;

import com.panata.cilindros.entity.eventuales;

public interface IEventualesService {


	public void save(eventuales a);
	public eventuales findById(Integer id);
	public void delete(Integer id);
	public List<eventuales> findAll();
	public List<eventuales> findBytipo(int tipo);
	public Integer restaPrestadoEventual();
	public Integer sumaPedidoEventual();
	public List<eventuales> findBytipoEventuales(int tipo);
	public Integer TotalEventuales(int tipo, int estado);
}
