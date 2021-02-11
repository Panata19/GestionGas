package com.panata.cilindros.controller;

import java.util.List;

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
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.bind.support.SessionStatus;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.panata.cilindros.entity.Categoria;
import com.panata.cilindros.entity.Gastos;
import com.panata.cilindros.service.ICategoriaService;
import com.panata.cilindros.service.IGastosService;

@Controller
@SessionAttributes("gastos")
@RequestMapping(value = "/gastos")
public class GastosController {
	
	@Autowired 
	private IGastosService srvGastos;
	
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
		
		List<Gastos> gastos = srvGastos.findAll();

		model.addAttribute("gastos", gastos);
		model.addAttribute("title", "Listado de Gastoss");
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
				model.addAttribute("title", titulo);	
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
			return "redirect:/gastos/create3";
		}				
		return "redirect:/gastos/list";
	}	

	
	


}
