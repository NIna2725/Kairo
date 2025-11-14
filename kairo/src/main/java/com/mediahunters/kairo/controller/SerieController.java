package com.mediahunters.kairo.controller;

import java.util.List;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.mediahunters.kairo.model.Serie;
import com.mediahunters.kairo.service.business.SerieService;

@RestController
@RequestMapping("/series")
public class SerieController {

    private final SerieService serieService;

    public SerieController(SerieService serieService){
        this.serieService = serieService;
    }

    @GetMapping
    public List<Serie> listar(){
        return serieService.listar();
    }

    @PostMapping
    public Serie guardar(@RequestBody Serie serie){
        return serieService.guardar(serie);
    }

    @PutMapping("/{id}")
    public Serie actualizar(@PathVariable Long id, @RequestBody Serie serie){
        return serieService.actualizar(id, serie);
    }

    @DeleteMapping("/series/{id}")
    public String eliminar(@PathVariable Long id){
        serieService.eliminar(id);
        return "Serie eliminada";
    }
    
}
