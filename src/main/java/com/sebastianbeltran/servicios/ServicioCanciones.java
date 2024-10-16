package com.sebastianbeltran.servicios;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.sebastianbeltran.modelos.Cancion;
import com.sebastianbeltran.repositorios.RepositorioCanciones;

@Service
public class ServicioCanciones {
	
	@Autowired
	private final RepositorioCanciones repositorioCanciones = null;
	
	public List<Cancion> obtenerTodasLasCanciones() {
        return this.repositorioCanciones.findAll();
    }

    public Cancion obtenerCancionPorId(Long id) {
        return this.repositorioCanciones.findById(id).orElse(null);
    }
    
    public Cancion agregarCancion(Cancion cancion) {
        return this.repositorioCanciones.save(cancion);
    }
    
    public Cancion actualizaCancion(Cancion cancion) {
        return this.repositorioCanciones.save(cancion);
    }
    
    public void eliminaCancion(Long id) {
        this.repositorioCanciones.deleteById(id);
    }
    
}
