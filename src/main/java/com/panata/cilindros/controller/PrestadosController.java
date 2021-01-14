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

import com.panata.cilindros.entity.prestados;
import com.panata.cilindros.service.IPrestadosService;

@Controller
@SessionAttributes("pretado")
@RequestMapping(value="/prestado")  
public class PrestadosController {
	
	@Autowired 
	private IPrestadosService srvPrestado;
	

	@GetMapping(value="/create") //https://localhost:8080/area/create
	public String create(Model model) {
		prestados prest = new prestados();
		
		model.addAttribute("title", "Cilindros prestados");
		model.addAttribute("prestado", prest); //similar al ViewBag // Se agrega a Session
		return "prestado/form"; //la ubicación de la vista
	}
	

	@GetMapping(value="/retrieve/{id}")
	public String retrieve(@PathVariable(value="id") Integer id, Model model) {
		prestados prest = srvPrestado.findById(id);
		if(prest.getTipo() == 1 ) {
			model.addAttribute("title", "Cilindro de 15 kg");
		}else {model.addAttribute("title", "Cilindro de 45 kg");}
		model.addAttribute("presttual", prest);		
		return "prestado/card";
	}
	
	@GetMapping(value="/update/{id}")
	public String update(@PathVariable(value="id") Integer id, Model model) {
		prestados prest = srvPrestado.findById(id);
	
		Calendar fecha = Calendar.getInstance();
		prest.setFecha_devuelto(fecha);
		prest.setActivo(2);
		srvPrestado.save(prest);	
		
		if(prest.getTipo() == 1 ) {
			return "redirect:/prestado/list15kg";
			
		}else {
			return "redirect:/prestado/list45kg";
			}
		
		
	}
	
	
	@GetMapping(value="/delete/{id}")
	public String delete(@PathVariable(value="id") Integer id, Model model, RedirectAttributes flash) {
		try {
			srvPrestado.delete(id);
			flash.addFlashAttribute("success", "Prestado eliminado con exito");
		}	
		catch(Exception ex) {
			flash.addFlashAttribute("error", "Error al eliminar.");
		}
		
		return "redirect:/prestado/list";		
	}
	//Pedidos prestados
	@GetMapping(value={"/list15kg"})
	public String list15kg(Model model) {
		List<prestados> prest = srvPrestado.findByTipo(1);
		model.addAttribute("rojo", srvPrestado.total(1, 1));
		model.addAttribute("verde", srvPrestado.total(1, 2));
		model.addAttribute("prestados", prest);
		model.addAttribute("title", "Cilindros de 15 kg");
		return "prestado/list15kg";
	}
	
	//Prestamos prestados
	@GetMapping(value={"/list45kg"})
	public String listprestamos(Model model) {
		List<prestados> prest = srvPrestado.findByTipo(2);
		model.addAttribute("rojo", srvPrestado.total(2, 1));
		model.addAttribute("verde", srvPrestado.total(2, 2));
		model.addAttribute("prestados", prest);
		model.addAttribute("title", "Cilindros de 45 kg");
		return "prestado/list45kg";
	}
	

	@PostMapping(value="/save") 
	public String save(@Validated prestados prestado, BindingResult result, Model model,
			
			SessionStatus status, RedirectAttributes flash) {
		try {
			
			String message = "Cilindro prestado correctamente";
			String titulo = "Nuevo prestamos o pedido presttual";
			
			if(prestado.getIdPrestados() != null) {
				message = "actualizada correctamente";
				titulo = "Actualizado correctamente";
			}
						
			if(result.hasErrors()) {
				model.addAttribute("title", titulo);							
				return "prestado/form";				
			}
						
			prestado.setActivo(1);
			srvPrestado.save(prestado);	
			status.setComplete();
			flash.addFlashAttribute("success", message);
		}
		catch(Exception ex) {
			flash.addFlashAttribute("error", ex.getMessage());
		}				
		return "redirect:/prestado/create";
	}	


	

}
