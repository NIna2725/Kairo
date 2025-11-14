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

    public Serie actualizar(Long id, Serie datos){
        Serie actual = repo.findById(id).orElseThrow();

        actual.setTitulo(datos.getTitulo());
        actual.setDescripcion(datos.getDescripcion());
        actual.setTemporadas(datos.getTemporadas());
        actual.setPlataforma(datos.getPlataforma());

        return repo.save(actual);
    }

    public void eliminar(Long id){
        repo.deleteById(id);
    }
}
