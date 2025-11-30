package com.mediahunters.kairo.model;

import jakarta.persistence.*;

@Entity
@Table(name = "favoritos")
public class Favorito {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private Long usuarioId;

    private Long contenidoId; // id de la película/serie/canción

    private String tipo; // pelicula, serie, cancion

    public Favorito() {
    }

    public Favorito(Long usuarioId, Long contenidoId, String tipo) {
        this.usuarioId = usuarioId;
        this.contenidoId = contenidoId;
        this.tipo = tipo;
    }

    // GETTERS Y SETTERS

    public Long getId() {
        return id;
    }

    public Long getUsuarioId() {
        return usuarioId;
    }

    public void setUsuarioId(Long usuarioId) {
        this.usuarioId = usuarioId;
    }

    public Long getContenidoId() {
        return contenidoId;
    }

    public void setContenidoId(Long contenidoId) {
        this.contenidoId = contenidoId;
    }

    public String getTipo() {
        return tipo;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }
}
