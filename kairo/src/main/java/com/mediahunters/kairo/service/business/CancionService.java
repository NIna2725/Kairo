package com.mediahunters.kairo.service.business;

import java.util.List;

import org.springframework.lang.NonNull;
import org.springframework.stereotype.Service;

import com.mediahunters.kairo.model.Cancion;
import com.mediahunters.kairo.repository.CancionRepository;

@Service
public class CancionService {
    private final CancionRepository repo;

    public CancionService(CancionRepository repo) {
        this.repo = repo;
    }

    public List<Cancion> listar() {
        return repo.findAll();
    }

    public Cancion guardar(Cancion c) {
        return repo.save(c);
    }

    public Cancion actualizar(Long id, Cancion datos) {
        Cancion actual = repo.findById(id).orElseThrow();

        actual.setTitulo(datos.getTitulo());
        actual.setDescripcion(datos.getDescripcion());
        actual.setAnio(datos.getAnio());
        actual.setArtista(datos.getArtista());

        return repo.save(actual);
    }

    public void eliminar(@NonNull Long id) {
        repo.deleteById(id);
    }
}
