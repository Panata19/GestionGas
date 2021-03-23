package com.panata.cilindros.controller;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.bind.support.SessionStatus;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.panata.cilindros.entity.Categoria;
import com.panata.cilindros.entity.Gastos;
import com.panata.cilindros.reporting.RptCategoriaGastos;
import com.panata.cilindros.service.ICategoriaService;
import com.panata.cilindros.service.IGastosService;
import com.panata.cilindros.service.IIngresosService;


@Controller
@SessionAttributes("gastos")
@RequestMapping(value = "/gastos")
public class GastosController {
	
	@Autowired 
	private IGastosService srvGastos;
	
	@Autowired 
	private IIngresosService srvIngreso;
	
	@Autowired 
	private ICategoriaService srvCategoria;
	
	@GetMapping(value="/create") //https://localhost:8080/area/create
	public String create(Model model) {
		Gastos gastos = new Gastos();
		List<Categoria> categorias = srvCategoria.findAll();
		
		model.addAttribute("categorias", categorias);
		model.addAttribute("title", "Nuevo registro de Gastos");
		model.addAttribute("gastos", gastos); //similar al ViewBag // Se agrega a Session
		return "gastos/form"; //la ubicación de la vista
	}
	
	
	@GetMapping(value="/retrieve/{id}")
	public String retrieve(@PathVariable(value="id") Integer id, Model model) {
		Gastos gastos = srvGastos.findById(id);
		model.addAttribute("gastos", gastos);		
		return "gastos/card";
	}
	
	
	@GetMapping(value="/update/{id}")
	public String update(@PathVariable(value="id") Integer id, Model model) {
		Gastos gastos = srvGastos.findById(id);
		List<Categoria> categorias = srvCategoria.findAll();
		
		model.addAttribute("categorias", categorias);
		model.addAttribute("title", "Nuevo registro de Gastos");
		model.addAttribute("gastos", gastos); //similar al ViewBag // Se agrega a Session
		return "gastos/form"; //la ubicación de la vista
	
	}
	
	
	@GetMapping(value="/delete/{id}")
	public String delete(@PathVariable(value="id") Integer id, Model model, RedirectAttributes flash) {
		try {
			srvGastos.delete(id);
			flash.addFlashAttribute("success", "Gastos elimnado con exito.");
		}	
		catch(Exception ex) {
			flash.addFlashAttribute("error", "Error al eliminar.");
		}
		
		return "redirect:/gastos/list";		
	}
	
	
	
	
	@GetMapping(value={"/list"})
	public String listpedidos(Model model) {
		
		Calendar diaActual = Calendar.getInstance();
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");	
		List<Gastos> gastos = srvGastos.findByFecha(sdf.format(diaActual.getTime()));
		List<String> categoria =  new ArrayList<String>();
		for(Gastos g:gastos) {
			categoria.add(g.getCategoria().getNombre());
		}
		categoria = categoria.stream().distinct().collect(Collectors.toList());
		
		
		
		Calendar primerDiaMes = Calendar.getInstance();
		primerDiaMes.set(Calendar.DAY_OF_MONTH, 1);
		
		
		Calendar ultimoDiaMes = Calendar.getInstance();
		ultimoDiaMes.set(Calendar.DATE, ultimoDiaMes.getActualMaximum(Calendar.DATE));
				
		Float gasMensual =srvGastos.sumatoriaMensualGastos(sdf.format(primerDiaMes.getTime()), sdf.format(ultimoDiaMes.getTime()));
		Float ingMensual =srvIngreso.sumatoriaMensualIngreso(sdf.format(primerDiaMes.getTime()), sdf.format(ultimoDiaMes.getTime()));
		Float gananciaMensual = ingMensual - gasMensual;  
		
		
		model.addAttribute("categorias", categoria );
		model.addAttribute("gastos", gastos);
		model.addAttribute("gastomensual",  gasMensual );
		model.addAttribute("ingresomensual", ingMensual );
		model.addAttribute("ganancias", gananciaMensual  );
	    model.addAttribute("title", "Administración de gastos e ingresos");
		return "gastos/list";
	}
	
	
	@PostMapping(value="/save") 
	public String save(@Validated Gastos gastos, BindingResult result, Model model,
			
			SessionStatus status, RedirectAttributes flash) {
		try {
			Categoria categoria = srvCategoria.findById(gastos.getIdCategoria());
			String message = "Gastos agregado correctamente";
			String titulo = "Nueva Gastos";
			List<Categoria> categorias = srvCategoria.findAll();
			
			if(gastos.getIdGastos() != null) {
				message = "Gastos actualizada correctamente";
				titulo = "Actualizado correctamente";
			}
			
			
			
			if(categoria == null) {
				flash.addFlashAttribute("error", "No se ha encontrado la categoria");
				return "redirect:/gastos/create";
			}
						
			if(result.hasErrors()) {
				model.addAttribute("errors", result.getFieldErrors());
				model.addAttribute("title", "Error al registrar");	
				model.addAttribute("categorias", categorias);
				System.out.println(result.getAllErrors());
				return "gastos/form";				
			}
					
			
			gastos.setCategoria(categoria);
			srvGastos.save(gastos);	
			status.setComplete();
			flash.addFlashAttribute("success", message);
		}
		catch(Exception ex) {
			flash.addFlashAttribute("error", ex.getMessage());
			ex.printStackTrace();
			return "redirect:/gastos/create";
		}				
		return "redirect:/gastos/list";
	}	

	@GetMapping(value = "/reporte")
	public String rptGastosCategorias(Model model) {
		return "gastos/reporte";			
	}
	
	@GetMapping(value = "/todasGastos/{fecha1}/{fecha2}", produces="application/json")
	public @ResponseBody List<RptCategoriaGastos> todasGastos(@PathVariable(value="fecha1") String fecha1,@PathVariable(value="fecha2") String fecha2, Model model) {				
		try {	
			
			SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");	
			List<Gastos> gastos = srvGastos.findGastosFiniFfin(fecha1,fecha2);
			List<String> categoria =  new ArrayList<String>();
			List<RptCategoriaGastos> reporte = new ArrayList<RptCategoriaGastos>();
			Float sumatoria=(float) 0;
			
			
			
			
			for(Gastos g:gastos) {
				categoria.add(g.getCategoria().getNombre());
			}
			categoria = categoria.stream().distinct().collect(Collectors.toList());
			
			//gastos de con la categoria resprectiva 
			
			for(String g:categoria) {
				sumatoria=(float) 0;
				for(int i=0 ; i<gastos.size();i++) {
					if(g==gastos.get(i).getCategoria().getNombre()) {
						sumatoria=sumatoria+gastos.get(i).getCantidad();
					}	
				}
				
				RptCategoriaGastos datos = new RptCategoriaGastos();
				datos.setCategoria(g);
				datos.setGasto(sumatoria);
				reporte.add(datos);
				
			}
			
			Float totalIngresos = srvIngreso.sumatoriaMensualIngreso(fecha1, fecha2);
			
			RptCategoriaGastos datos = new RptCategoriaGastos();
			datos.setCategoria("Ingresos de la fecha");
			datos.setGasto(totalIngresos);
			reporte.add(datos);
			
			return reporte;
		} catch (Exception ex) {
			System.out.println(ex.getMessage());
			return null;
		}		
	}
	
	
	
	
	//Cantidad de los gastos ganancias y ingresos del dia 
	@GetMapping(value = "/gastosdeldia", produces="application/json")
	public @ResponseBody List<RptCategoriaGastos> gastosdeldia( Model model) {				
		try {	
			
			Calendar diaActual = Calendar.getInstance();
			SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");	
			
			Float gastos = srvGastos.sumatoriaDiaGastos(sdf.format(diaActual.getTime()));
			Float ingresos = srvIngreso.sumatoriaDiaIngreso(sdf.format(diaActual.getTime()));
			Float ganancias = ingresos - gastos;
			
			
			List<RptCategoriaGastos> reporte = new ArrayList<RptCategoriaGastos>();
			
				RptCategoriaGastos datos = new RptCategoriaGastos();
				datos.setCategoria("gasto");
				datos.setGasto(gastos);
				reporte.add(datos);
				
				
				RptCategoriaGastos datos1 = new RptCategoriaGastos();
				datos1.setCategoria("ingresos");
				datos1.setGasto(ingresos);
				reporte.add(datos1);
				
				
				RptCategoriaGastos datos2 = new RptCategoriaGastos();
				datos2.setCategoria("ganancias");
				datos2.setGasto(ganancias);
				reporte.add(datos2);
				
				
			return reporte;
		} catch (Exception ex) {
			System.out.println(ex.getMessage());
			return null;
		}		
	}
	

	
	
	


}
