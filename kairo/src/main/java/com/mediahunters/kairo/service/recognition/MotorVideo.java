package com.mediahunters.kairo.service.recognition;

import org.springframework.stereotype.Component;

@Component
public class MotorVideo {
    
    public String reconocerEscena(byte[] video){
        // Lógica simulada de reconocimiento de video
        // En un caso real, aquí se integraría con una librería o servicio de reconocimiento de video
        return "Escena reconocida: 'Atardecer en la playa'";
    }
}
