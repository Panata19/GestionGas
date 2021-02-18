package com.panata.cilindros.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.panata.cilindros.dao.IIngresos;
import com.panata.cilindros.entity.Ingreso;

@Service
public class IngresosService implements IIngresosService {
	
	@Autowired
	private IIngresos dao;
	
	@Override
	@Transactional
	public void save(Ingreso a) {
		
		dao.save(a);
	}

	@Override
	@Transactional
	public Ingreso findById(Integer id) {
		
		return dao.findById(id).get();
	}

	@Override
	@Transactional
	public void delete(Integer id) {
		
		dao.deleteById(id);
		
	}

	@Override
	@Transactional
	public List<Ingreso> findAll() {
		
		return (List<Ingreso>) dao.findAll();
	}

	@Override
	@Transactional
	public Float sumatoriaMensualIngreso(String fe_inicial, String fe_final) {
		
		
		Float res = dao.sumatoriaMensualIngreso(fe_inicial, fe_final);
		if(res == null) {
			res = (float)0;
		}
		
		return res;
	}

	@Override
	@Transactional
	public Float sumatoriaDiaIngreso(String fe_dia) {
		
		Float res = dao.sumatoriaDiaIngreso(fe_dia);
		if(res == null) {
			res = (float)0;
		}
		
		return res;
		
	}


}
