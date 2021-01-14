package com.panata.cilindros.service;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.panata.cilindros.dao.IPrestados;
import com.panata.cilindros.entity.prestados;

@Service
public class PrestadosService implements IPrestadosService{

	@Autowired 
	private IPrestados dao;
	
	@Override
	@Transactional
	public void save(prestados a) {
		// TODO Auto-generated method stub
		dao.save(a);
		
	}

	@Override
	@Transactional
	public prestados findById(Integer id) {
		// TODO Auto-generated method stub
		return dao.findById(id).get();
	}

	@Override
	@Transactional
	public void delete(Integer id) {
		// TODO Auto-generated method stub
		dao.deleteById(id);
	}

	@Override
	@Transactional
	public List<prestados> findAll() {
		// TODO Auto-generated method stub
		return ( List<prestados>)  dao.findAll();
	}

	@Override
	@Transactional
	public List<prestados> findByTipo(int tipo) {
		
		return ( List<prestados>)  dao.findBytipo(tipo);
	}

	@Override
	@Transactional
	public Integer sumaPrestados() {
		// TODO Auto-generated method stub
		return dao.sumaPrestados();
	}

	@Override
	@Transactional
	public List<prestados> findBytipoPrestados() {
		// TODO Auto-generated method stub
		return dao.findBytipoPrestados();
	}

	@Override
	@Transactional
	public Integer total(int tipo, int activo) {
		// TODO Auto-generated method stub
		return dao.total(tipo, activo);
	}
	

}
