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

import com.panata.cilindros.entity.eventuales;

import com.panata.cilindros.service.IEventualesService;


@Controller
@SessionAttributes("eventual")
@RequestMapping(value="/eventual")  
public class EventualesController {

	@Autowired 
	private IEventualesService srvEventuales;
	
	
	@GetMapping(value="/create") //https://localhost:8080/area/create
	public String create(Model model) {
		eventuales even = new eventuales();
		
		model.addAttribute("title", "Nuevo registro de área");
		model.addAttribute("eventual", even); //similar al ViewBag // Se agrega a Session
		return "eventual/form"; //la ubicación de la vista
	}
	

	@GetMapping(value="/retrieve/{id}")
	public String retrieve(@PathVariable(value="id") Integer id, Model model) {
		eventuales even = srvEventuales.findById(id);
		if(even.getTipo() == 1 ) {
			model.addAttribute("title", "Prestado a Segundo");
		}else {model.addAttribute("title", "Pedido a Segundo");}
		model.addAttribute("eventual", even);		
		return "eventual/card";
	}
	
	@GetMapping(value="/update/{id}")
	public String update(@PathVariable(value="id") Integer id, Model model) {
		eventuales even = srvEventuales.findById(id);
		
		Calendar fecha = Calendar.getInstance();
		even.setFecha_devolucion(fecha);
		even.setEstado(2);
		srvEventuales.save(even);	
		
		if(even.getTipo() == 1 ) {
			return "redirect:/eventual/listpedidos";
			
		}else {
			return "redirect:/eventual/listprestamos";
			}
	
	}
	
	
	@GetMapping(value="/delete/{id}")
	public String delete(@PathVariable(value="id") Integer id, Model model, RedirectAttributes flash) {
		try {
			srvEventuales.delete(id);
			flash.addFlashAttribute("success", "Prestamo eventual elimnado con exito.");
		}	
		catch(Exception ex) {
			flash.addFlashAttribute("error", "Error al eliminar.");
		}
		
		return "redirect:/eventual/list";		
	}
	//Pedidos eventuales
	@GetMapping(value={"/listpedidos"})
	public String listpedidos(Model model) {
		List<eventuales> even = srvEventuales.findBytipo(1);
		
		model.addAttribute("verde", srvEventuales.TotalEventuales(1, 1));
		model.addAttribute("rojo", srvEventuales.TotalEventuales(1, 2));
		model.addAttribute("eventuales", even);
		model.addAttribute("title", "Leo pidio eventualmente");
		return "eventual/listpedidos";
	}
	
	//Prestamos eventuales
	@GetMapping(value={"/listprestamos"})
	public String listprestamos(Model model) {
		List<eventuales> even = srvEventuales.findBytipo(2);
		model.addAttribute("verde", srvEventuales.TotalEventuales(2, 1));
		model.addAttribute("rojo", srvEventuales.TotalEventuales(2, 2));
		model.addAttribute("eventuales", even);
		model.addAttribute("title", "leo presto eventualmente");
		return "eventual/listprestamos";
	}
	

	@PostMapping(value="/save") 
	public String save(@Validated eventuales eventual, BindingResult result, Model model,
			
			SessionStatus status, RedirectAttributes flash) {
		try {
			
			String message = "Prestamos eventuales agregado correctamente";
			String titulo = "Nuevo prestamos o pedido eventual";
			
			if(eventual.getIdeventual() != null) {
				message = "Prestamo actualizada correctamente";
				titulo = "Actualizado correctamente";
			}
						
			if(result.hasErrors()) {
				model.addAttribute("title", titulo);							
				return "eventual/form";				
			}
						
			eventual.setEstado(1);
			srvEventuales.save(eventual);	
			status.setComplete();
			flash.addFlashAttribute("success", message);
		}
		catch(Exception ex) {
			flash.addFlashAttribute("error", ex.getMessage());
		}				
		return "redirect:/eventual/create";
	}	

	
	
	
}
