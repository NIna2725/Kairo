package com.mediahunters.kairo.service.business;

import java.util.List;

import org.springframework.stereotype.Service;

import com.mediahunters.kairo.model.Historial;
import com.mediahunters.kairo.repository.HistorialRepository;

@Service
public class HistorialService {

    private final HistorialRepository historialRepo;

    public HistorialService(HistorialRepository historialRepo) {
        this.historialRepo = historialRepo;
    }

    public Historial guardar(Historial h) {
        return historialRepo.save(h);
    }

    public List<Historial> listarPorUsuario(Long usuarioId) {
        return historialRepo.findByUsuarioIdOrderByFechaVisualizacionDesc(usuarioId);
    }
}
