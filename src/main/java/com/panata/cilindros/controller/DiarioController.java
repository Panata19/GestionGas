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

import com.panata.cilindros.entity.diario;
import com.panata.cilindros.entity.eventuales;
import com.panata.cilindros.entity.prestados;
import com.panata.cilindros.service.IDiarioService;
import com.panata.cilindros.service.IEventualesService;
import com.panata.cilindros.service.IPrestadosService;

@Controller
@SessionAttributes("diar")
@RequestMapping(value = "/diario")
public class DiarioController {

	@Autowired
	private IDiarioService srvDiario;

	@Autowired
	private IPrestadosService srvPrestado;

	@Autowired
	private IEventualesService srvEventuales;

	@GetMapping(value = "/create") // https://localhost:8080/area/create
	public String create(Model model) {
		diario diar;
		// Calendar cal = Calendar.getInstance();
		Calendar calendar = Calendar.getInstance();
		calendar.set(Calendar.HOUR_OF_DAY, 0);
		calendar.set(Calendar.MINUTE, 0);
		calendar.set(Calendar.SECOND, 0);
		calendar.set(Calendar.MILLISECOND, 0);

		Integer a = srvDiario.ultimoRegistro();

		if (a != null) {
			diar = srvDiario.findById(a.intValue());
			diar.setNota1("");
			diar.setNota2("");
			diar.setNota3("");
			
			Calendar cali = diar.getFecha_diario();

			if (cali.compareTo(calendar) == 0) {
				return "redirect:/diario/error";
			}

		} else {
			diar = new diario();
		}

		model.addAttribute("title", "Nuevo registro Diario");
		model.addAttribute("diario", diar); // similar al ViewBag // Se agrega a Session
		return "diario/form"; // la ubicación de la vista
	}

	@GetMapping(value = "/error")
	public String error() {

		return "diario/error";
	}

	@GetMapping(value = "/retrieve/{id}")
	public String retrieve(@PathVariable(value = "id") Integer id, Model model) {
		diario diar = srvDiario.findById(id);
		model.addAttribute("diario", diar);
		return "diario/card";
	}

	@GetMapping(value = "/delete/{id}")
	public String delete(@PathVariable(value = "id") Integer id, Model model, RedirectAttributes flash) {
		try {
			srvDiario.delete(id);
			flash.addFlashAttribute("success", "Control Diario elimnado con exito.");
		} catch (Exception ex) {
			flash.addFlashAttribute("error", "Error al eliminar.");
		}

		return "redirect:/diario/list";
	}

	// Pedidos diario
	@GetMapping(value = { "/list" })
	public String listpedidos(Model model) {
		List<diario> diar = srvDiario.findAll();
		model.addAttribute("diario", diar);
		model.addAttribute("title", "Pedidos diario");
		return "diario/list";
	}

	@PostMapping(value = "/save")
	public String save(@Validated diario diar, BindingResult result, Model model,

			SessionStatus status, RedirectAttributes flash) {
		try {

			String message = "Control diario Correcto";
			String titulo = "Nuevo Control Diario";

			if (diar.getIdDiario() != null) {
				message = "Prestamo actualizada correctamente";
				titulo = "Actualizado correctamente";
			}

			if (result.hasErrors()) {
				model.addAttribute("title", titulo);
				return "diario/form";
			}

			Integer sumaprestados15kg = srvPrestado.sumaPrestados();
			Integer sumaEventual = 0, restaEventual = 0;

			if (sumaprestados15kg != null)
				diar.setPrestados_cantidad(sumaprestados15kg);
			else {
				diar.setPrestados_cantidad(0);
			}

			Integer prestadoEventuales = srvEventuales.restaPrestadoEventual();
			Integer pedidosEventuales = srvEventuales.sumaPedidoEventual();
			if (prestadoEventuales != null)
				diar.setPrestadoseven_cantidad(prestadoEventuales);
			else
				diar.setPrestadoseven_cantidad(0);

			if (pedidosEventuales != null)
				diar.setPedidoseven_cantidad(pedidosEventuales);
			else
				diar.setPedidoseven_cantidad(0);

			// Guardar Nombres de todos
			List<eventuales> evenpedido = srvEventuales.findBytipo(1);
			List<eventuales> evenprestado = srvEventuales.findBytipo(2);
			List<prestados> negocio = srvPrestado.findBytipoPrestados();
			String per1 = "";
			String per2 = "";
			String per3 = "";
			for (int i = 0; i < evenpedido.size(); i++) {
				per1 = evenpedido.get(i).getResponsable() + " " + evenpedido.get(i).getCantidad() + ", " + per1;
			}

			for (int i = 0; i < evenprestado.size(); i++) {
				per2 = evenprestado.get(i).getResponsable() + " " + evenprestado.get(i).getCantidad() + ", " + per2;
			}

			for (int i = 0; i < negocio.size(); i++) {
				per3 = negocio.get(i).getResponsable() + " " + negocio.get(i).getCantidad() + ", " + per3;
			}

			diar.setPedidoseven_personas(per1);
			diar.setPrestadoseven_personas(per2);
			diar.setPrestados_personas(per3);

			int suma1 = diar.getFc_cantidad() + diar.getGd_cantidad() + diar.getHg_cantidad()
					+ diar.getFarfan_cantidad() + diar.getLago_cantidad() + diar.getInoperativos_cantidad()
					+ diar.getIndustriales_cantidad() + diar.getPrestados_cantidad() + diar.getPrestadoseven_cantidad()
					- diar.getPedidoseven_cantidad();
			suma1 = suma1 + diar.getAdicional1_cant() + diar.getAdicional2_cant() + diar.getAdicional3_cant();
			int suma2 = diar.getRosita_cantidad() + diar.getLeonardo_cantidad() + diar.getLeoncio_cantidad()
					+ diar.getJaramillo_cantidad() + diar.getSilvia_cantidad();

			if (suma1 != suma2) {
				flash.addFlashAttribute("error", " " + "Ubicaciòn=" + suma1 + " Dueños=" + suma2);
				return "redirect:/diario/create";
			}

			Calendar fecha = Calendar.getInstance();

			diar.setFecha_diario(fecha);
			srvDiario.save(diar);
			status.setComplete();
			flash.addFlashAttribute("success", message);
		} catch (Exception ex) {
			flash.addFlashAttribute("error", ex.getMessage());
		}
		return "redirect:/diario/list";
	}

}
