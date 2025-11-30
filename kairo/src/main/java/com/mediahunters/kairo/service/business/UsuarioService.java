package com.mediahunters.kairo.service.business;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import com.mediahunters.kairo.model.Usuario;
import com.mediahunters.kairo.repository.UsuarioRepository;

@Service
public class UsuarioService {

    private final UsuarioRepository usuarioRepo;
    private final BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();

    public UsuarioService(UsuarioRepository usuarioRepo) {
        this.usuarioRepo = usuarioRepo;
    }

    public Usuario obtenerPorId(Long id) {
        return usuarioRepo.findById(id)
                .orElseThrow(() -> new RuntimeException("Usuario no encontrado"));
    }

    public Usuario actualizarUsuario(Long id, String nombre, String email) {
        Usuario u = obtenerPorId(id);
        u.setNombre(nombre);
        u.setEmail(email);
        return usuarioRepo.save(u);
    }

    public Usuario buscarPorEmail(String email) {
        Usuario usuario = usuarioRepo.findByEmail(email);
        if (usuario == null) {
            throw new RuntimeException("Usuario no encontrado con email: " + email);
        }
        return usuario;
    }

    public void cambiarPassword(Long id, String actual, String nueva) {
        Usuario u = obtenerPorId(id);

        // MODO DESARROLLO: Sin encriptación
        if (!actual.equals(u.getPassword())) {
            throw new RuntimeException("Contraseña actual incorrecta");
        }

        // MODO DESARROLLO: Guardar contraseña sin encriptar
        u.setPassword(nueva);
        usuarioRepo.save(u);
    }
}
