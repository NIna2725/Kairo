package com.mediahunters.kairo.service.recognition;

import java.util.List;

import org.springframework.stereotype.Service;

import com.mediahunters.kairo.model.Cancion;
import com.mediahunters.kairo.model.Historial;
import com.mediahunters.kairo.model.Pelicula;
import com.mediahunters.kairo.model.Serie;
import com.mediahunters.kairo.repository.CancionRepository;
import com.mediahunters.kairo.repository.PeliculaRepository;
import com.mediahunters.kairo.repository.SerieRepository;
import com.mediahunters.kairo.service.business.HistorialService;

/**
 * Complementa el reconocimiento textual devolviendo datos existentes en la
 * base, de modo
 * que el frontend pueda mostrar contexto (título, año, temporadas, etc.) junto
 * al resultado
 * del motor de reconocimiento.
 */

@Service
public class RecognitionBDService {

    private final PeliculaRepository peliculaRepo;
    private final SerieRepository serieRepo;
    private final CancionRepository cancionRepo;
    private final HistorialService historialService;

    public RecognitionBDService(PeliculaRepository peliculaRepo, SerieRepository serieRepo,
            CancionRepository cancionRepo, HistorialService historialService) {
        this.peliculaRepo = peliculaRepo;
        this.serieRepo = serieRepo;
        this.cancionRepo = cancionRepo;
        this.historialService = historialService;
    }

    public String reconocerTextoEnBD(Long usuarioId, String texto) {

        List<Pelicula> peliculas = peliculaRepo.buscarPorTexto(texto);
        if (!peliculas.isEmpty()) {
            Pelicula p = peliculas.get(0);

            // TODO: Implement history tracking once Usuario and Contenido relationships are
            // properly set up
            // historialService.guardar(historial);

            return "Pelicula reconocida: " + p.getTitulo() + " ( " + p.getAnio() + " )";
        }

        List<Serie> series = serieRepo.buscarPorTexto(texto);
        if (!series.isEmpty()) {
            Serie s = series.get(0);

            // TODO: Implement history tracking once Usuario and Contenido relationships are
            // properly set up
            // historialService.guardar(historial);

            return "Serie reconocida: " + s.getTitulo() + " ( " + s.getTemporadas() + " )";
        }

        List<Cancion> cancions = cancionRepo.buscarPorTexto(texto);
        if (!cancions.isEmpty()) {
            Cancion c = cancions.get(0);

            // TODO: Implement history tracking once Usuario and Contenido relationships are
            // properly set up
            // historialService.guardar(historial);

            return "Cancion reconocida: " + c.getTitulo() + " ( " + c.getAnio() + " )";
        }

        // no encontrado
        return "No se encontro ninguna coincidencia en la base de datos.";
    }
}
