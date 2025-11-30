package com.mediahunters.kairo.controller;

import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.mediahunters.kairo.dto.AuthResponse;
import com.mediahunters.kairo.dto.LoginRequest;
import com.mediahunters.kairo.model.Usuario;
import com.mediahunters.kairo.service.auth.AuthService;

@RestController
@RequestMapping("/auth")
@CrossOrigin("*")
public class AuthController {

    private final AuthService authService;

    public AuthController(AuthService authService) {
        this.authService = authService;
    }

    @PostMapping("/login")
    public AuthResponse login(@RequestBody LoginRequest login) {
        return authService.login(login);
    }

    @PostMapping("/register")
    public org.springframework.http.ResponseEntity<?> register(@RequestBody Usuario u) {
        try {
            return org.springframework.http.ResponseEntity.ok(authService.register(u));
        } catch (RuntimeException e) {
            return org.springframework.http.ResponseEntity
                    .status(org.springframework.http.HttpStatus.BAD_REQUEST)
                    .body(java.util.Map.of("message", e.getMessage()));
        }
    }
}
