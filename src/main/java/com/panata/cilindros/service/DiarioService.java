package com.panata.cilindros.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.panata.cilindros.dao.IDiario;
import com.panata.cilindros.entity.diario;


@Service
public class DiarioService implements IDiarioService {


	@Autowired
	private IDiario dao;
	
	@Override
	@Transactional
	public void save(diario a) {
		
		dao.save(a);
	}

	@Override
	@Transactional
	public diario findById(Integer id) {
		
		return dao.findById(id).get();
	}

	@Override
	@Transactional
	public void delete(Integer id) {
		
		dao.deleteById(id);
		
	}

	@Override
	@Transactional
	public List<diario> findAll() {
		
		return (List<diario>) dao.findAll();
	}


	@Override
	public Integer ultimoRegistro() {
		// TODO Auto-generated method stub
		return dao.ultimoRegistro();
	}



}
