package com.mediahunters.kairo.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.mediahunters.kairo.model.Pelicula;

public interface PeliculaRepository extends JpaRepository<Pelicula, Long>{
    @Query("SELECT p FROM Pelicula p WHERE " +
       "LOWER(p.titulo) LIKE LOWER(CONCAT('%', :texto, '%')) OR " +
       "LOWER(p.descripcion) LIKE LOWER(CONCAT('%', :texto, '%'))")
    List<Pelicula> buscarPorTexto(@Param("texto") String texto);

}
