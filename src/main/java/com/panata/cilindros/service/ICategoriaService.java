package com.panata.cilindros.service;

import java.util.List;

import com.panata.cilindros.entity.Categoria;

public interface ICategoriaService {

	public void save(Categoria a);
	public Categoria findById(Integer id);
	public void delete(Integer id);
	public List<Categoria> findAll();
}
