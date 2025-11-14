package com.mediahunters.kairo.service.business;

import java.util.List;

import org.springframework.stereotype.Service;

import com.mediahunters.kairo.model.Pelicula;
import com.mediahunters.kairo.repository.PeliculaRepository;

@Service
public class PeliculaService {
    
    private final PeliculaRepository repo;

    public  PeliculaService(PeliculaRepository repo){
        this.repo = repo;
    }

    public List<Pelicula> listar(){
        return repo.findAll();
    }

    public Pelicula guardar(Pelicula p){
        return repo.save(p);
    }
}
