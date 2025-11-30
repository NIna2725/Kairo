package com.mediahunters.kairo.controller;

import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.mediahunters.kairo.model.Usuario;
import com.mediahunters.kairo.patterns.facade.GestorReconocimiento;
import com.mediahunters.kairo.service.recognition.RecognitionBDService;
import com.mediahunters.kairo.service.recognition.RecognitionResponse;

@RestController
@RequestMapping("/recognition")
public class RecognitionController {

    private final GestorReconocimiento gestor;
    private final RecognitionBDService recognitionBDService;
    private final com.mediahunters.kairo.service.business.UsuarioService usuarioService;

    public RecognitionController(GestorReconocimiento gestor, RecognitionBDService recognitionBDService,
            com.mediahunters.kairo.service.business.UsuarioService usuarioService) {
        this.gestor = gestor;
        this.recognitionBDService = recognitionBDService;
        this.usuarioService = usuarioService;
    }

    @PostMapping("/audio")
    public RecognitionResponse reconocerAudio(@RequestBody byte[] audio) {
        String resultado = gestor.reconocerAudio(audio);
        return RecognitionResponse.soloResultado(resultado);
    }

    @PostMapping("/video")
    public RecognitionResponse reconocerVideo(@RequestBody byte[] video) {
        String resultado = gestor.reconocerVideo(video);
        return RecognitionResponse.soloResultado(resultado);
    }

    @PostMapping("/texto")
    public RecognitionResponse reconocerTexto(@RequestBody String texto, Authentication authentication) {
        // 1. Extraer el JSON si viene como JSON string (parche rápido)
        if (texto.startsWith("{") && texto.contains(":")) {
            // Esto es muy básico, idealmente usar un DTO o JsonNode
            // Pero dado que el frontend manda {"texto":"payaso"}
            // Vamos a limpiar un poco
            texto = texto.replace("{\"texto\":\"", "").replace("\"}", "");
        }

        String reconocimiento = gestor.reconocerTexto(texto);

        // 2. Buscar usuario por email (el principal es el email)
        String email = authentication.getName();
        com.mediahunters.kairo.model.Usuario usuario = usuarioService.buscarPorEmail(email);
        Long usuarioId = usuario != null ? usuario.getId() : 0L;

        String contexto = recognitionBDService.reconocerTextoEnBD(usuarioId, texto);
        return new RecognitionResponse(reconocimiento, contexto);
    }
}
