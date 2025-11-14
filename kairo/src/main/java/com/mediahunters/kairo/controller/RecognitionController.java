package com.mediahunters.kairo.controller;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.mediahunters.kairo.patterns.facade.GestorReconocimiento;
import com.mediahunters.kairo.service.recognition.RecognitionBDService;

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
    public String reocnocerAudio(@RequestBody byte[] audio){
        return gestor.reconocerAudio(audio);
    }

    @PostMapping("/video")
    public String reconocerVideo(@RequestBody byte[] video){
        return gestor.reconoerVideo(video);
    }

    @PostMapping("/texto")
    public String reconocerTexto(@RequestBody String texto){
        return recognitionBDService.reconocerTextoEnBD(texto);
    }
}
