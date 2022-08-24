package com.ingenieriaweb.springboot.web.app.controllers;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import com.ingenieriaweb.springboot.web.app.services.Interface.RUTA_SERVICE;
import com.ingenieriaweb.springboot.web.app.services.Interface.USUARIO_SERVICE;


@Controller
public class HomeController {

	@Autowired
	private RUTA_SERVICE rutaService;
	@Autowired
	private USUARIO_SERVICE usuarioService;
	//---------------------------ADMINISTRADOR------------------------------------------
	/*
	@GetMapping("/bienvenido")
	public String home(Model model) {
		model.addAttribute("titulo","Bienvenido");
		return "inicio";
	}
	*/
	
	//---------------------------diseñoYisus------------------------------------------
	
	
	@GetMapping("/prueba/aea/lol/pimin")
	public String prueba(Model model)
	{
		//model.addAttribute("ruta", rutaService.findByOtraCosaAndPimin("C","XX"));
		model.addAttribute("sesion", usuarioService.getUsuario());
		return "prueba";
	}
	@GetMapping("/bienvenido")
	public String welcome(Model model) {
		return "diseñoYisus/inicio";
	}
	@GetMapping("/venta-bus" )
	public String venta_bus(Model model) {
		return "diseñoYisus/venta-bus";
	}
	
	@GetMapping("/venta-asiento" )
	public String venta_asiento(Model model) {
		return "diseñoYisus/venta-asiento";
	}
	
	@GetMapping("/venta-pasajero" )
	public String venta_pasajero(Model model) {
		return "diseñoYisus/venta-pasajero";
	}
	
	@GetMapping("/venta-pago" )
	public String venta_pago(Model model) {
		return "diseñoYisus/venta-pago";
	}
}

