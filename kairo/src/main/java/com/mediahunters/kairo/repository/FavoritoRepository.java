package com.mediahunters.kairo.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.mediahunters.kairo.model.Favorito;

public interface FavoritoRepository extends JpaRepository<Favorito, Long> {

    List<Favorito> findByUsuarioId(Long usuarioId);

    Favorito findByUsuarioIdAndContenidoId(Long usuarioId, Long contenidoId);

    void deleteByUsuarioIdAndContenidoId(Long usuarioId, Long contenidoId);
}
