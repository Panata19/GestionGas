package com.panata.cilindros.service;

import java.sql.Date;
import java.util.Calendar;
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

	@Override
	@Transactional
	public List<Gastos> findByFecha(String fecha) {
		// TODO Auto-generated method stub
		return (List<Gastos>) dao.findByFecha(fecha);
	}

	@Override
	@Transactional
	public Float sumatoriaMensualGastos(String fe_inicial, String fe_final) {
		
		Float res = dao.sumatoriaMensualGastos(fe_inicial, fe_final);
		if (res == null) {
			
			res = (float) 0;
		}
		
		return res;
	}

	@Override
	@Transactional
	public Float sumatoriaDiaGastos(String fe_dia) {
		
		Float res = dao.sumatoriaDiaGastos(fe_dia);
		if (res == null) {
			
			res = (float) 0;
		}
		
		return res;
	}

	@Override
	@Transactional
	public List<Gastos> findGastosFiniFfin(String fe_inicial, String fe_final) {
		// TODO Auto-generated method stub
		return (List<Gastos>) dao.findGastosFiniFfin(fe_inicial, fe_final);
	}

	
	

}
