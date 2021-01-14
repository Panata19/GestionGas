package com.panata.cilindros.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.panata.cilindros.dao.IEventuales;
import com.panata.cilindros.entity.eventuales;

@Service
public class EventualesService implements IEventualesService{

	@Autowired
	private IEventuales dao;
	
	@Override
	@Transactional
	public void save(eventuales a) {
		dao.save(a);
		
	}

	@Override
	@Transactional
	public eventuales findById(Integer id) {
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
	public List<eventuales> findAll() {
		// TODO Auto-generated method stub
		return (List<eventuales>) dao.findAll();
	}

	@Override
	@Transactional
	public List<eventuales> findBytipo(int tipo) {
		// TODO Auto-generated method stub
		return (List<eventuales>) dao.findBytipo(tipo);
	}

	@Override
	@Transactional
	public Integer restaPrestadoEventual() {
		// TODO Auto-generated method stub
		return dao.restaPrestadoEventual();
	}

	@Override
	@Transactional
	public Integer sumaPedidoEventual() {
		// TODO Auto-generated method stub
		return dao.sumaPedidoEventual();
	}

	@Override
	@Transactional
	public List<eventuales> findBytipoEventuales(int tipo) {
		// TODO Auto-generated method stub
		return dao.findBytipoEventuales(tipo);
	}

	@Override
	@Transactional
	public Integer TotalEventuales(int tipo, int estado) {
		// TODO Auto-generated method stub
		return dao.TotalEventuales(tipo, estado);
	}

}
