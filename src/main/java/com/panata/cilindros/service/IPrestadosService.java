package com.panata.cilindros.service;

import java.util.List;

import com.panata.cilindros.entity.prestados;

public interface IPrestadosService {


	public void save(prestados a);
	public prestados findById(Integer id);
	public void delete(Integer id);
	public List<prestados> findAll();
	public List<prestados> findByTipo(int tipo);
	public Integer sumaPrestados();
	public List<prestados> findBytipoPrestados();
	public Integer total(int tipo, int activo);
}
