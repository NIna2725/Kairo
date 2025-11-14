package com.mediahunters.kairo.controller;

import java.util.List;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.mediahunters.kairo.model.Cancion;
import com.mediahunters.kairo.model.Pelicula;
import com.mediahunters.kairo.model.Serie;

import com.mediahunters.kairo.service.business.PeliculaService;
import com.mediahunters.kairo.service.business.CancionService;
import com.mediahunters.kairo.service.business.SerieService;



@RestController
@RequestMapping("/content")
public class ContentController {
    
    private final CancionService cancionService;
    private final PeliculaService peliculaService;
    private final SerieService serieService;

    public ContentController(
        CancionService cancionService, 
        PeliculaService peliculaService, 
        SerieService serieService){
        this.cancionService = cancionService;
        this.peliculaService = peliculaService;
        this.serieService = serieService;
    }

    @GetMapping("/peliculas")
    public List<Pelicula> listarPeliculas(){
        return peliculaService.listar();
    }
    @PostMapping("/peliculas")
    public Pelicula crearPelicula(@RequestBody Pelicula pelicula){
        // Logs para debug
        return peliculaService.guardar(pelicula);
    }

    @GetMapping("/series")
    public List<Serie> listarSeries(){
        return serieService.listar();
    }
    

    @PostMapping("/series")
    public Serie crearSerie(@RequestBody Serie serie){
        // Logs para debug

        return serieService.guardar(serie);
    }

    @GetMapping("/canciones")
    public List<Cancion> listarCanciones(){
        return cancionService.listar();
    }
    
    @PostMapping("/canciones")
    public Cancion crearCancion(@RequestBody Cancion cancion){
        // Logs para debug

        return cancionService.guardar(cancion);
    }
}
