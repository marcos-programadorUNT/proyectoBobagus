package com.ingenieriaweb.springboot.web.app.controllers;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.support.SessionStatus;

import com.ingenieriaweb.springboot.web.app.models.*;
import com.ingenieriaweb.springboot.web.app.services.Interface.*;

@Controller
public class LoginController {
	
	@Autowired
    private BCryptPasswordEncoder passwordEncoder;
    @Autowired
    private USUARIO_SERVICE usuarioService;
    @Autowired
    private ADMINISTRADOR_SERVICE administradorService;
    @Autowired
    private SUCURSAL_SERVICE sucursalService;


    @Autowired
    private ROL_SERVICE rolService;

    @RequestMapping(value = {"","/"},method = RequestMethod.GET)
    public String index (Model model){
        model.addAttribute("titulo","Página de Inicio");
        USUARIO usuario = usuarioService.getUsuario();
        model.addAttribute("titulo","Página de Inicio");
        model.addAttribute("usuario",usuario);
        model.addAttribute("sesion",usuario);
        return "inicio";
    }

    @RequestMapping(value = "/login",method = RequestMethod.GET)
    public String IniciarSesion (Model model){
        model.addAttribute("titulo", "Inicio de sesión");
        return "autenticacion/login";
    }

    @RequestMapping(value = "/registrar",method = RequestMethod.GET)
    public String registroCliente (Model model){
        USUARIO usuario=new USUARIO();
        model.addAttribute("usuario",usuario);
        //model.addAttribute("titulo", "Registro de usuario");
        return "autenticacion/registrar";
    }

    @RequestMapping(value = "/registrar", method = RequestMethod.POST)
    public String guardar(@Valid USUARIO usuario, BindingResult result, Model model, SessionStatus status) {
    	 if(result.hasErrors()) {
             model.addAttribute("usuario",usuario);
             return "autenticacion/registrar";
         }
         ROL rol = rolService.findByDescripcion("administrador");
         usuario.addRol(rol);
         usuario.setPassword(passwordEncoder.encode(usuario.getPassword()));
         usuarioService.save(usuario);
         
         /*
         ADMINISTRADOR administrador = new ADMINISTRADOR();
         administrador.setIdAdministrador(usuarioService.findTopByOrderByIdDesc().getId());
         administrador.setSucursal(sucursalService.findByIdSucursal(Long.valueOf(1)));
         administrador.setUsuario(usuarioService.findTopByOrderByIdDesc());
         administradorService.save(administrador);
         */
         
         /*
         System.out.println(usuarioService.findTopByOrderByIdDesc().getId());
         System.out.println(sucursalService.findByIdSucursal(Long.valueOf(1)).getDireccion());
         System.out.println(usuarioService.findTopByOrderByIdDesc().getNombre());
		*/
         
         
         status.setComplete();
         return "redirect:/";
    }
}
