package com.mediahunters.kairo.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.mediahunters.kairo.model.Usuario;

public interface UsuarioRepository extends JpaRepository<Usuario, Long> {
    Usuario findByEmail(String email);
}
