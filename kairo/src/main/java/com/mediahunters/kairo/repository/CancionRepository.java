package com.mediahunters.kairo.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.mediahunters.kairo.model.Cancion;

public interface CancionRepository extends JpaRepository<Cancion, Long>{

    @Query("SELECT c FROM Cancion c WHERE " +
       "LOWER(c.titulo) LIKE LOWER(CONCAT('%', :texto, '%')) OR " +
       "LOWER(c.artista) LIKE LOWER(CONCAT('%', :texto, '%'))")
List<Cancion> buscarPorTexto(@Param("texto") String texto);

} 
