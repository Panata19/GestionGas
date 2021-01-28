package com.panata.cilindros.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.panata.cilindros.dao.ICategoria;
import com.panata.cilindros.entity.Categoria;

@Service
public class CategoriaService implements ICategoriaService {

	@Autowired
	private ICategoria dao;
	
	@Override
	@Transactional
	public void save(Categoria a) {
		
		dao.save(a);
	}

	@Override
	@Transactional
	public Categoria findById(Integer id) {
		
		return dao.findById(id).get();
	}

	@Override
	@Transactional
	public void delete(Integer id) {
		
		dao.deleteById(id);
		
	}

	@Override
	@Transactional
	public List<Categoria> findAll() {
		
		return (List<Categoria>) dao.findAll();
	}




}
