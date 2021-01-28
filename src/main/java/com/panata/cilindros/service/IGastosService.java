package com.panata.cilindros.service;

import java.util.List;

import com.panata.cilindros.entity.Gastos;

public interface IGastosService {

	public void save(Gastos a);
	public Gastos findById(Integer id);
	public void delete(Integer id);
	public List<Gastos> findAll();
}
