package com.sebastianbeltran.controladores;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;

import com.sebastianbeltran.modelos.Cancion;
import com.sebastianbeltran.servicios.ServicioCanciones;

import jakarta.validation.Valid;

@Controller
public class ControladorCanciones {
	
	@Autowired
	private ServicioCanciones servicioCanciones;
	
    @GetMapping("/canciones")
    public String desplegarCanciones(Model modelo) {
        modelo.addAttribute("canciones", servicioCanciones.obtenerTodasLasCanciones());
        return "canciones.jsp";
    }
    
    @GetMapping("/canciones/detalle/{idCancion}")
    public String desplegarDetalleCancion(@PathVariable Long idCancion, Model modelo) {
        modelo.addAttribute("cancion", servicioCanciones.obtenerCancionPorId(idCancion));
        return "detalleCancion.jsp";
    }
    
    @GetMapping("/canciones/formulario/agregar")
    public String formularioAgregarCancion(@ModelAttribute Cancion cancion) {
        return "agregarCancion.jsp";
    }
    
    @PostMapping("/canciones/procesa/agregar")
    public String procesarAgregarCancion(@Valid @ModelAttribute Cancion cancion, 
    									BindingResult validaciones, Model modelo) {
        if (validaciones.hasErrors()) {
            return "agregarCancion.jsp";
        }
        this.servicioCanciones.agregarCancion(cancion);
        return "redirect:/canciones";
    }
    
    @GetMapping("/canciones/formulario/editar/{idCancion}")
    public String muestraFormularioEditarCancion(@PathVariable Long idCancion, Model modelo) {
        Cancion cancionActual = this.servicioCanciones.obtenerCancionPorId(idCancion);
        modelo.addAttribute("cancion", cancionActual);
        return "editarCancion.jsp";
    }

    @PutMapping("/canciones/procesa/editar/{idCancion}")
    public String procesarEditarCancion(@PathVariable Long idCancion, 
                                        @Valid @ModelAttribute Cancion cancion, 
                                        BindingResult validaciones) {
        if (validaciones.hasErrors()) {
            return "editarCancion.jsp";
        }
        cancion.setId(idCancion);
        this.servicioCanciones.actualizaCancion(cancion);
        return "redirect:/canciones";
    }
    
    @DeleteMapping("/canciones/eliminar/{idCancion}")
    public String procesarEliminarCancion(@PathVariable Long idCancion) {
        this.servicioCanciones.eliminaCancion(idCancion);
        return "redirect:/canciones";
    }
}
