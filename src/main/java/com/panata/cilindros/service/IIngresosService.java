package com.panata.cilindros.service;

import java.util.List;

import com.panata.cilindros.entity.Ingreso;

public interface IIngresosService {

	public void save(Ingreso a);
	public Ingreso findById(Integer id);
	public void delete(Integer id);
	public List<Ingreso> findAll();
	public Float sumatoriaMensualIngreso(String  fe_inicial, String  fe_final);
	public Float sumatoriaDiaIngreso(String  fe_dia);
}
