package com.mediahunters.kairo.service.business;

import java.util.List;

import org.springframework.stereotype.Service;

import com.mediahunters.kairo.model.Favorito;
import com.mediahunters.kairo.repository.FavoritoRepository;

@Service
public class FavoritoService {

    private final FavoritoRepository favoritoRepo;

    public FavoritoService(FavoritoRepository favoritoRepo) {
        this.favoritoRepo = favoritoRepo;
    }

    public Favorito agregarFavorito(Favorito f) {
        // evitar duplicados
        Favorito existente = favoritoRepo.findByUsuarioIdAndContenidoId(f.getUsuarioId(), f.getContenidoId());

        if (existente != null) {
            return existente; // ya estaba guardado
        }

        return favoritoRepo.save(f);
    }

    public List<Favorito> listarFavoritos(Long usuarioId) {
        return favoritoRepo.findByUsuarioId(usuarioId);
    }

    public void eliminarFavorito(Long usuarioId, Long contenidoId) {
        favoritoRepo.deleteByUsuarioIdAndContenidoId(usuarioId, contenidoId);
    }
}
