package com.mediahunters.kairo.controller;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.mediahunters.kairo.model.Usuario;
import com.mediahunters.kairo.service.business.UsuarioService;

@RestController
@RequestMapping("/auth")
public class AuthController {
    
    private final UsuarioService service;

    public AuthController(UsuarioService service){
        this.service = service;
    }

    @PostMapping("/register")

    public Usuario registrar(@RequestBody Usuario u){
        return service.registrar(u);
    }
}
