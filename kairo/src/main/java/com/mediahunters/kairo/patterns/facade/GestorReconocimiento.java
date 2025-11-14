package com.mediahunters.kairo.patterns.facade;

import org.springframework.stereotype.Service;

import com.mediahunters.kairo.service.recognition.MotorAudio;
import com.mediahunters.kairo.service.recognition.MotorTexto;
import com.mediahunters.kairo.service.recognition.MotorVideo;

@Service
public class GestorReconocimiento {
    
private final MotorAudio motorAudio;
private final MotorVideo motorVideo;
private final MotorTexto motorTexto;

public GestorReconocimiento(MotorAudio motorAudio, MotorVideo motorVideo, MotorTexto motorTexto){
    this.motorAudio = motorAudio;
    this.motorVideo = motorVideo;
    this.motorTexto = motorTexto;
}
    public String reconocerAudio(byte[] audioData){
        return motorAudio.ronocerCancion(audioData);
    }

    public String reconoerVideo(byte[] videoData){
        return motorVideo.reconocerEscena(videoData);
    }

    public String reconocertexto(String frase){
        return motorTexto.reonocerFrases(frase);
    }
}
