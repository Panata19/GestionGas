package com.panata.cilindros.service;

import java.sql.Date;
import java.util.Calendar;
import java.util.List;

import com.panata.cilindros.entity.Gastos;

public interface IGastosService {

	public void save(Gastos a);
	public Gastos findById(Integer id);
	public void delete(Integer id);
	public List<Gastos> findAll();
	public List<Gastos> findByFecha(String fecha);
	public Float sumatoriaMensualGastos(String  fe_inicial, String  fe_final);
	public Float sumatoriaDiaGastos(String  fe_dia );
	public List<Gastos> findGastosFiniFfin(String  fe_inicial, String  fe_final);
	
}
