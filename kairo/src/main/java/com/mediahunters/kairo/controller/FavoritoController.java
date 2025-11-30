package com.mediahunters.kairo.controller;

import java.util.List;

import org.springframework.web.bind.annotation.*;

import com.mediahunters.kairo.model.Favorito;
import com.mediahunters.kairo.service.business.FavoritoService;

@RestController
@RequestMapping("/favoritos")
@CrossOrigin("*")
public class FavoritoController {

    private final FavoritoService favoritoService;

    public FavoritoController(FavoritoService favoritoService) {
        this.favoritoService = favoritoService;
    }

    @PostMapping("/add")
    public Favorito agregar(@RequestBody Favorito f) {
        return favoritoService.agregarFavorito(f);
    }

    @GetMapping("/{usuarioId}")
    public List<Favorito> listar(@PathVariable Long usuarioId) {
        return favoritoService.listarFavoritos(usuarioId);
    }

    @DeleteMapping("/{usuarioId}/{contenidoId}")
    public void eliminar(@PathVariable Long usuarioId,
            @PathVariable Long contenidoId) {
        favoritoService.eliminarFavorito(usuarioId, contenidoId);
    }
}
