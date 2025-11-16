package com.mediahunters.kairo.controller;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.mediahunters.kairo.patterns.facade.GestorReconocimiento;
import com.mediahunters.kairo.service.recognition.RecognitionBDService;
import com.mediahunters.kairo.service.recognition.RecognitionResponse;

@RestController
@RequestMapping("/recognition")
public class RecognitionController {
    
    private final GestorReconocimiento gestor;
    private final RecognitionBDService recognitionBDService;

    public RecognitionController(GestorReconocimiento gestor, RecognitionBDService recognitionBDService){
        this.gestor = gestor;
        this.recognitionBDService = recognitionBDService;
    }

    @PostMapping("/audio")
    public RecognitionResponse reconocerAudio(@RequestBody byte[] audio){
        String resultado = gestor.reconocerAudio(audio);
        return RecognitionResponse.soloResultado(resultado);
    }

    @PostMapping("/video")
    public RecognitionResponse reconocerVideo(@RequestBody byte[] video){
        String resultado = gestor.reconocerVideo(video);
        return RecognitionResponse.soloResultado(resultado);
    }

    @PostMapping("/texto")
     public RecognitionResponse reconocerTexto(@RequestBody String texto){
        String reconocimiento = gestor.reconocerTexto(texto);
        String contexto = recognitionBDService.reconocerTextoEnBD(texto);
        return new RecognitionResponse(reconocimiento, contexto);
    }
}
