package com.mediahunters.kairo.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.mediahunters.kairo.model.Historial;

public interface HistorialRepository extends JpaRepository<Historial, Long> {

    List<Historial> findByUsuarioIdOrderByFechaVisualizacionDesc(Long usuarioId);
}
