package com.mediahunters.kairo.service.auth;

import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.mediahunters.kairo.dto.AuthResponse;
import com.mediahunters.kairo.dto.LoginRequest;
import com.mediahunters.kairo.model.Usuario;
import com.mediahunters.kairo.repository.UsuarioRepository;
import com.mediahunters.kairo.security.JwtUtil;

@Service
public class AuthService {

    private final JwtUtil jwUtil;
    private final UsuarioRepository usuarioRepo;
    private final PasswordEncoder passwordEncoder;

    public AuthService(JwtUtil jwUtil, UsuarioRepository usuarioRepo, PasswordEncoder passwordEncoder) {
        this.jwUtil = jwUtil;
        this.usuarioRepo = usuarioRepo;
        this.passwordEncoder = passwordEncoder;
    }

    public AuthResponse login(LoginRequest request) {
        System.out.println("=== LOGIN REQUEST (KAIRO) ===");
        System.out.println("Email buscando: " + request.getEmail());

        Usuario usuario = usuarioRepo.findByEmail(request.getEmail());

        if (usuario == null) {
            System.out.println("❌ Usuario NO encontrado en base de datos");
            throw new RuntimeException("Credenciales inválidas - Usuario no existe");
        }

        System.out.println("✅ Usuario encontrado: " + usuario.getNombre());

        // Usar encriptación correcta
        if (!passwordEncoder.matches(request.getPassword(), usuario.getPassword())) {
            System.out.println("❌ Contraseña NO coincide");
            throw new RuntimeException("Credenciales inválidas - Contraseña incorrecta");
        }

        System.out.println("✅ Login Exitoso");
        String token = jwUtil.generateToken(usuario.getEmail());
        return new AuthResponse(token, usuario.getId());
    }

    public Usuario register(Usuario u) {
        // Verificar si ya existe
        if (usuarioRepo.findByEmail(u.getEmail()) != null) {
            throw new RuntimeException("El correo ya está registrado");
        }

        // Encriptar contraseña antes de guardar
        u.setPassword(passwordEncoder.encode(u.getPassword()));
        return usuarioRepo.save(u);
    }
}
