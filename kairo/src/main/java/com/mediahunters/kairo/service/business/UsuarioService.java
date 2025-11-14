package com.mediahunters.kairo.service.business;

import java.util.List;

import org.springframework.stereotype.Service;

import com.mediahunters.kairo.model.Usuario;
import com.mediahunters.kairo.repository.UsuarioRepository;

@Service
public class UsuarioService {
    
    private final UsuarioRepository repo;

    public UsuarioService(UsuarioRepository repo){
        this.repo = repo;
    }

    public Usuario registar(Usuario u){
        return repo.save(u);
    }

    public List<Usuario> listar(){
        return repo.findAll();
    }

}
