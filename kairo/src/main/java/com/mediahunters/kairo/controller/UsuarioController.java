package com.mediahunters.kairo.controller;

import org.springframework.web.bind.annotation.*;

import com.mediahunters.kairo.dto.UpdateUserRequest;
import com.mediahunters.kairo.dto.ChangePasswordRequest;
import com.mediahunters.kairo.model.Usuario;
import com.mediahunters.kairo.service.business.UsuarioService;

@RestController
@RequestMapping("/usuarios")
@CrossOrigin("*")
public class UsuarioController {

    private final UsuarioService usuarioService;

    public UsuarioController(UsuarioService usuarioService) {
        this.usuarioService = usuarioService;
    }

    @GetMapping("/{id}")
    public Usuario obtenerUsuario(@PathVariable Long id) {
        return usuarioService.obtenerPorId(id);
    }

    @GetMapping("/buscar")
    public Usuario buscarPorEmail(@RequestParam String email) {
        return usuarioService.buscarPorEmail(email);
    }

    @PutMapping("/{id}")
    public Usuario actualizarUsuario(@PathVariable Long id,
            @RequestBody UpdateUserRequest request) {
        return usuarioService.actualizarUsuario(id, request.getNombre(), request.getEmail());
    }

    @PutMapping("/password/{id}")
    public String cambiarPassword(@PathVariable Long id,
            @RequestBody ChangePasswordRequest request) {

        usuarioService.cambiarPassword(id,
                request.getPasswordActual(),
                request.getNuevaPassword());

        return "Contraseña actualizada correctamente";
    }
}
