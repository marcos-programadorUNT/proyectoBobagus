package com.ingenieriaweb.springboot.web.app.controllers;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.ingenieriaweb.springboot.web.app.dao.*;
import com.ingenieriaweb.springboot.web.app.models.*;
import com.ingenieriaweb.springboot.web.app.services.*;
import com.ingenieriaweb.springboot.web.app.services.Interface.*;


@Controller
@RequestMapping("/compraBoletos")
public class CompraBoletosController {
	List<SELECCION> listadoSelecciones;
	CRONOGRAMA_VIAJE varCronogramaIda;
	CRONOGRAMA_VIAJE varCronogramaVuelta;
	List<Object[]>  varPisosAsientosIda;
	List<Object[]>  varPisosAsientosVuelta;
	@Autowired
	private RUTA_SERVICE rutaService;
	@Autowired
	private CRONOGRAMA_VIAJE_SERVICE cronogramaViajeService;
	@Autowired
	private ASIENTO_SERVICE asientoService;
	
	@GetMapping("/inicio")
	public String inicio(Model model) {
		model.addAttribute("rutasOrigen", rutaService.soloRutasOrigenDistintas());
		model.addAttribute("rutasDestino", rutaService.soloRutasDestinoDistintas());
		return "cliente/boletos/inicio";
	}
	
	@PostMapping("/cronogramas")
	public String mostrarCronogramas(Model model, 
									@RequestParam Long idOrigen,
									@RequestParam Long idDestino,
									@RequestParam @DateTimeFormat(pattern = "yyyy-MM-dd") Date fecha_ida,
									@RequestParam @DateTimeFormat(pattern = "yyyy-MM-dd") Date fecha_retorno) {
			
		//Obtenermos la ruta para la ida
		RUTA rutaIda = rutaService.rutaSegunIdOrigenYIdDestino(idOrigen, idDestino);
		//Obtenemos la ruta para el retorno
		RUTA rutaRetorno = rutaService.rutaSegunIdOrigenYIdDestino(idDestino,idOrigen);
	
		//Enviamos la ruta
		model.addAttribute("ruta",rutaIda);
		//Obtenemos los cronogramas pertenecientes a esa RUT_id y tal fecha. Despues enviamos a la vista como "cronogramasIda" y "cronogramasVuelta"
		model.addAttribute("cronogramasIda", cronogramaViajeService.buscarCronogramasPorRutaYFecha(rutaIda.getIdRuta(),fecha_ida));
		model.addAttribute("cronogramasVuelta", cronogramaViajeService.buscarCronogramasPorRutaYFecha(rutaRetorno.getIdRuta(),fecha_retorno));
	
		return "cliente/boletos/cronogramas";
	}
	
	@PostMapping("/guardarCronogramas")
	public String guardarCronogramas(Model model,
									@RequestParam(name="idCronogramaIda", required=false) Long idCronogramaIda,
									@RequestParam(name="idCronogramaVuelta", required=false) Long idCronogramaVuelta
									) {
		
		SELECCION seleccion = new SELECCION();
		seleccion.setCronogramaIda(cronogramaViajeService.findByIdCronograma(idCronogramaIda));
		seleccion.setCronogramaVuelta(cronogramaViajeService.findByIdCronograma(idCronogramaVuelta));
		seleccion.setIdSeleccion(UUID.randomUUID().toString());
		
		if(listadoSelecciones==null) listadoSelecciones=new ArrayList<>();
		listadoSelecciones.add(seleccion);
		
		for(SELECCION sel:listadoSelecciones)
		{
			System.out.println(sel.toString());
		}
		
		//System.out.println(idCronogramaIda);
		//System.out.println(idCronogramaVuelta);

	
		return "redirect:/compraBoletos/asientos/"+seleccion.getIdSeleccion();
	}
	
	@GetMapping("/asientos/{idSeleccion}")
	public String verAsientos(Model model,
							  @PathVariable(name="idSeleccion", required=false) String idSeleccion
							 )
	{
		
		
		for(SELECCION sel:listadoSelecciones)
		{
			if(sel.getIdSeleccion().equals(idSeleccion))
			{
				if(sel.getCronogramaIda().getIdCronograma()!=null)
				{
					varCronogramaIda=cronogramaViajeService.findByIdCronograma(sel.getCronogramaIda().getIdCronograma());
					varPisosAsientosIda=cronogramaViajeService.buscarPisosYAsientos(sel.getCronogramaIda().getIdCronograma());
				}
				else
				{
					varCronogramaIda=null;
				}
				
				if(sel.getCronogramaIda().getIdCronograma()!=null && sel.getCronogramaVuelta().getIdCronograma()!=null)
				{
					varCronogramaVuelta=cronogramaViajeService.findByIdCronograma(sel.getCronogramaVuelta().getIdCronograma());
					varPisosAsientosVuelta=cronogramaViajeService.buscarPisosYAsientos(sel.getCronogramaVuelta().getIdCronograma());
				}
				else
				{
					varCronogramaVuelta=null;
				}
			}
		}
		
		for(Object[] v:varPisosAsientosVuelta)
		{
			System.out.println(v[1]+" "+v[2]);
		}
		
		model.addAttribute("pisosAsientosIda",varPisosAsientosIda);
		model.addAttribute("pisosAsientosVuelta",varPisosAsientosVuelta);
		model.addAttribute("cronogramaIda",varCronogramaIda);
		model.addAttribute("cronogramaVuelta",varCronogramaVuelta);
		model.addAttribute("idSeleccion",idSeleccion);
		return "cliente/boletos/asientos";
		
	}
	
	@GetMapping(value = "/agregarAsientoLista/{idSeleccion}/{idAsiento}/{tipoCronograma}", produces = { "application/json" })
	public @ResponseBody void agregarAsientoLista(@PathVariable String idSeleccion, @PathVariable Long idAsiento,@PathVariable String tipoCronograma) {
		
		for(SELECCION sel:listadoSelecciones)
		{
			List<BOLETO> listadoBoletos = new ArrayList<>();
			if(sel.getIdSeleccion().equals(idSeleccion))
			{
				//Buscamos el asiento
				ASIENTO asiento=asientoService.findByIdAsiento(idAsiento);
				
				//Creamos boletos
				BOLETO boleto = new BOLETO();
				boleto.setAsiento(asiento);
				boleto.setCostoAsiento(asiento.getCosto());
				boleto.setDescuentoAsiento(asiento.getDescuento());
				if(tipoCronograma=="ida")
					boleto.setCronograma_viaje(sel.getCronogramaIda());
				else
				{
				if(tipoCronograma=="vuelta")
					boleto.setCronograma_viaje(sel.getCronogramaVuelta());
				}
				listadoBoletos.add(boleto);
				
				
				//Se añade el boleto a la lista
				sel.setBoletos(listadoBoletos);
				
				for(BOLETO bol:sel.getBoletos())
				{
					System.out.println(bol.getAsiento().getIdAsiento());
				}
			}
		}
		
		System.out.println(idSeleccion+" "+idAsiento+" "+tipoCronograma);
	}
	
	
	
	
	
	
	
	
	

	
}
