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
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.bind.support.SessionStatus;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.panata.cilindros.entity.Ingreso;
import com.panata.cilindros.service.IIngresosService;

@Controller
@SessionAttributes("ingreso")
@RequestMapping(value = "/ingreso")
public class IngresosController {

	@Autowired 
	private IIngresosService srvIngreso;
	
	@GetMapping(value="/create") //https://localhost:8080/area/create
	public String create(Model model) {
		Ingreso ingreso = new Ingreso();
		
		model.addAttribute("title", "Nuevo registro de Ingreso");
		model.addAttribute("ingreso", ingreso); //similar al ViewBag // Se agrega a Session
		return "ingreso/form"; //la ubicación de la vista
	}
	
	
	@GetMapping(value="/retrieve/{id}")
	public String retrieve(@PathVariable(value="id") Integer id, Model model) {
		Ingreso ingreso = srvIngreso.findById(id);
		model.addAttribute("ingreso", ingreso);		
		return "ingreso/card";
	}
	
	
	@GetMapping(value="/update/{id}")
	public String update(@PathVariable(value="id") Integer id, Model model) {
		Ingreso ingreso = srvIngreso.findById(id);
		srvIngreso.save(ingreso);	
		return "redirect:/ingreso/list";
	
	}
	
	
	@GetMapping(value="/delete/{id}")
	public String delete(@PathVariable(value="id") Integer id, Model model, RedirectAttributes flash) {
		try {
			srvIngreso.delete(id);
			flash.addFlashAttribute("success", "Ingreso elimnado con exito.");
		}	
		catch(Exception ex) {
			flash.addFlashAttribute("error", "Error al eliminar.");
		}
		
		return "redirect:/ingreso/list";		
	}
	
	
	
	
	@GetMapping(value={"/list"})
	public String listpedidos(Model model) {
		
		List<Ingreso> ingreso = srvIngreso.findAll();
		model.addAttribute("ingresos", ingreso);
		model.addAttribute("title", "Listado de Ingresos");
		return "ingreso/list";
	}
	
	
	@PostMapping(value="/save") 
	public String save(@Validated Ingreso ingreso, BindingResult result, Model model,
			
			SessionStatus status, RedirectAttributes flash) {
		try {
			
			String message = "Ingreso agregado correctamente";
			String titulo = "Nueva Ingreso";
			
			if(ingreso.getIdIngreso() != null) {
				message = "Ingreso actualizada correctamente";
				titulo = "Actualizado correctamente";
			}
						
			if(result.hasErrors()) {
				model.addAttribute("title", result.toString());							
				return "ingreso/form";				
			}
						

			srvIngreso.save(ingreso);	
			status.setComplete();
			flash.addFlashAttribute("success", message);
		}
		catch(Exception ex) {
			flash.addFlashAttribute("error", ex.getMessage());
			ex.printStackTrace();
		}				
		return "redirect:/ingreso/list";
	}	

      
	
}
