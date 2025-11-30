package com.mediahunters.kairo.model;

import java.time.LocalDateTime;

import org.springframework.lang.NonNull;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class Historial {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private Long usuarioId;

    private String textoBuscado; // Título o texto de búsqueda

    private String tipo; // pelicula, serie, cancion

    private LocalDateTime fechaVisualizacion;

    // Constructor manual ya que Lombok a veces da problemas con cambios rápidos
    public Historial(Long usuarioId, String textoBuscado, String tipo, LocalDateTime fechaVisualizacion) {
        this.usuarioId = usuarioId;
        this.textoBuscado = textoBuscado;
        this.tipo = tipo;
        this.fechaVisualizacion = fechaVisualizacion;
    }
}
