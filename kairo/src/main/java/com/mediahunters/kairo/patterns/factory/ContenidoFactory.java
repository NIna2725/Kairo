package com.mediahunters.kairo.patterns.factory;

import com.mediahunters.kairo.model.Cancion;
import com.mediahunters.kairo.model.Contenido;
import com.mediahunters.kairo.model.Pelicula;
import com.mediahunters.kairo.model.Serie;

public class ContenidoFactory {
    public static Contenido crear(String tipo){
        return switch (tipo.toLowerCase()) {
            case "cancion" -> new Cancion();
            case "pelicula" -> new Pelicula();
            case "serie" -> new Serie();
            default -> throw new IllegalArgumentException("Tipo no reconocido: " + tipo);
        };
    }
}
