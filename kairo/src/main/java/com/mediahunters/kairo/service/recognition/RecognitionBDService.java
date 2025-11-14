package com.mediahunters.kairo.service.recognition;

import java.util.List;

import org.springframework.stereotype.Service;

import com.mediahunters.kairo.model.Cancion;
import com.mediahunters.kairo.model.Pelicula;
import com.mediahunters.kairo.model.Serie;
import com.mediahunters.kairo.repository.CancionRepository;
import com.mediahunters.kairo.repository.PeliculaRepository;
import com.mediahunters.kairo.repository.SerieRepository;

@Service
public class RecognitionBDService {
    
    private final PeliculaRepository peliculaRepo;
    private final SerieRepository serieRepo;
    private final CancionRepository cancionRepo;

    public RecognitionBDService(PeliculaRepository peliculaRepo, SerieRepository serieRepo, CancionRepository cancionRepo){
        this.peliculaRepo = peliculaRepo;
        this.serieRepo = serieRepo;
        this.cancionRepo = cancionRepo;
    }

    public String reconocerTextoEnBD(String texto){

        List<Pelicula> peliculas = peliculaRepo.buscarPorTexto(texto);
        if(!peliculas.isEmpty()){
            Pelicula p = peliculas.get(0);
            return "Pelicula reconocida: " +  p.getTitulo() + " ( " + p.getAnio() + " )";
        }

        List<Serie> series = serieRepo.buscarPorTexto(texto);
        if(!series.isEmpty()){
            Serie p = series.get(0);
            return "Serie reconocida: " +  p.getTitulo() + " ( " + p.getTemparadas() + " )";
        }

        List<Cancion> cancions = cancionRepo.buscarPorTexto(texto);
        if(!cancions.isEmpty()){
            Cancion p = cancions.get(0);
            return "Cancion reconocida: " +  p.getTitulo() + " ( " + p.getAnio() + " )";
        }

        return "No se encontro ninguna coincidencia en la base de datos.";
    }
}
