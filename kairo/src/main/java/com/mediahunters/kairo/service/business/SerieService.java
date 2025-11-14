package com.mediahunters.kairo.service.business;

import java.util.List;

import org.springframework.stereotype.Service;

import com.mediahunters.kairo.model.Serie;
import com.mediahunters.kairo.repository.SerieRepository;

@Service
public class SerieService {
    
    private final SerieRepository repo;

    public SerieService(SerieRepository repo){
        this.repo = repo;
    }
    public List<Serie> listar(){
        return repo.findAll();
    }

    public Serie guardar(Serie s){
        return repo.save(s);
    }
}
