package com.mediahunters.kairo.controller;

import java.util.List;

import org.springframework.web.bind.annotation.*;

import com.mediahunters.kairo.model.Historial;
import com.mediahunters.kairo.service.business.HistorialService;

@RestController
@RequestMapping("/historial")
@CrossOrigin("*")
public class HistorialController {

    private final HistorialService historialService;

    public HistorialController(HistorialService historialService) {
        this.historialService = historialService;
    }

    @GetMapping("/{usuarioId}")
    public List<Historial> obtenerHistorial(@PathVariable Long usuarioId) {
        return historialService.listarPorUsuario(usuarioId);
    }

    @PostMapping("/add")
    public Historial agregarHistorial(@RequestBody Historial h) {
        h.setFechaVisualizacion(java.time.LocalDateTime.now());
        return historialService.guardar(h);
    }
}
