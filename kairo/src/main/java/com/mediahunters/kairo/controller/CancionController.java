package com.mediahunters.kairo.controller;

import java.util.List;

import org.springframework.lang.NonNull;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.mediahunters.kairo.model.Cancion;
import com.mediahunters.kairo.service.business.CancionService;

@RestController
@RequestMapping("/canciones")
public class CancionController {
    private final CancionService cancionService;

    public CancionController(CancionService cancionService) {
        this.cancionService = cancionService;
    }

    @GetMapping
    public List<Cancion> listar() {
        return cancionService.listar();
    }

    @PostMapping
    public Cancion guardar(@RequestBody Cancion cancion) {
        return cancionService.guardar(cancion);
    }

    @PutMapping("/{id}")
    public Cancion actualizar(@PathVariable Long id, @RequestBody Cancion cancion) {
        return cancionService.actualizar(id, cancion);
    }

    @DeleteMapping("/{id}")
    public String eliminar(@PathVariable @NonNull Long id) {
        cancionService.eliminar(id);
        return "Canción eliminada";
    }
}
