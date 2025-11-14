package com.mediahunters.kairo.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.mediahunters.kairo.model.Serie;

public interface SerieRepository extends JpaRepository<Serie, Long>{
    @Query("SELECT s FROM Serie s WHERE " +
       "LOWER(s.titulo) LIKE LOWER(CONCAT('%', :texto, '%')) OR " +
       "LOWER(s.descripcion) LIKE LOWER(CONCAT('%', :texto, '%'))")
List<Serie> buscarPorTexto(@Param("texto") String texto);

}
