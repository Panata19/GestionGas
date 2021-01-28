package com.panata.cilindros.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.panata.cilindros.dao.IGastos;
import com.panata.cilindros.entity.Gastos;

@Service
public class GastosService implements  IGastosService  {

	@Autowired
	private IGastos dao;
	
	@Override
	@Transactional
	public void save(Gastos a) {
		
		dao.save(a);
	}

	@Override
	@Transactional
	public Gastos findById(Integer id) {
		
		return dao.findById(id).get();
	}

	@Override
	@Transactional
	public void delete(Integer id) {
		
		dao.deleteById(id);
		
	}

	@Override
	@Transactional
	public List<Gastos> findAll() {
		
		return (List<Gastos>) dao.findAll();
	}

}
