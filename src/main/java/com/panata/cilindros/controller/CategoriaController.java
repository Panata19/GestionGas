 package com.panata.cilindros.controller;

import java.util.Calendar;
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
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.bind.support.SessionStatus;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.panata.cilindros.entity.Categoria;
import com.panata.cilindros.service.ICategoriaService;

@Controller
@SessionAttributes("categoria")
@RequestMapping(value = "/categoria")
public class CategoriaController {
	
	@Autowired 
	private ICategoriaService srvCategoria;
	
	@GetMapping(value="/create") //https://localhost:8080/area/create
	public String create(Model model) {
		Categoria categoria = new Categoria();
		
		model.addAttribute("title", "Nuevo registro de Categoria");
		model.addAttribute("categoria", categoria); //similar al ViewBag // Se agrega a Session
		return "categoria/form"; //la ubicación de la vista
	}
	
	
	@GetMapping(value="/retrieve/{id}")
	public String retrieve(@PathVariable(value="id") Integer id, Model model) {
		Categoria categoria = srvCategoria.findById(id);
		model.addAttribute("categoria", categoria);		
		return "categoria/card";
	}
	
	
	@GetMapping(value="/update/{id}")
	public String update(@PathVariable(value="id") Integer id, Model model) {
		Categoria categoria = srvCategoria.findById(id);
		srvCategoria.save(categoria);	
		return "redirect:/categoria/list";
	
	}
	
	
	@GetMapping(value="/delete/{id}")
	public String delete(@PathVariable(value="id") Integer id, Model model, RedirectAttributes flash) {
		try {
			srvCategoria.delete(id);
			flash.addFlashAttribute("success", "Categoria elimnado con exito.");
		}	
		catch(Exception ex) {
			flash.addFlashAttribute("error", "Error al eliminar.");
		}
		
		return "redirect:/categoria/list";		
	}
	
	
	
	
	@GetMapping(value={"/list"})
	public String listpedidos(Model model) {
		List<Categoria> categorias = srvCategoria.findAll();
		
		model.addAttribute("categorias", categorias);
		model.addAttribute("title", "Listado de Categorias");
		return "categoria/list";
	}
	
	
	@PostMapping(value="/save") 
	public String save(@Validated Categoria categoria, BindingResult result, Model model,
			
			SessionStatus status, RedirectAttributes flash) {
		try {
			
			String message = "Categoria agregado correctamente";
			String titulo = "Nueva categoria";
			
			if(categoria.getIdCategoria() != null) {
				message = "Categoria actualizada correctamente";
				titulo = "Actualizado correctamente";
			}
						
			if(result.hasErrors()) {
				model.addAttribute("title", titulo);							
				return "categoria/form";				
			}
						

			srvCategoria.save(categoria);	
			status.setComplete();
			flash.addFlashAttribute("success", message);
		}
		catch(Exception ex) {
			flash.addFlashAttribute("error", ex.getMessage());
			ex.printStackTrace();
			return "redirect:/categoria/create";
		}				
		return "redirect:/categoria/list";
	}	

	
	
	
	
	

}
