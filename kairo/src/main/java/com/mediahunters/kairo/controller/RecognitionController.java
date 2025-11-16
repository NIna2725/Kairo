package com.mediahunters.kairo.controller;

import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.mediahunters.kairo.patterns.facade.GestorReconocimiento;
import com.mediahunters.kairo.service.recognition.AudioACRCloudService;
import com.mediahunters.kairo.service.recognition.RecognitionBDService;

@RestController
@RequestMapping("/recognition")
public class RecognitionController {
    
    private final GestorReconocimiento gestor;
    private final RecognitionBDService recognitionBDService;
    private final AudioACRCloudService audioService;

    public RecognitionController(GestorReconocimiento gestor, 
                                RecognitionBDService recognitionBDService, 
                                AudioACRCloudService audioService) {

        this.gestor = gestor;
        this.recognitionBDService = recognitionBDService;
        this.audioService = audioService;
    }

    @GetMapping("/test")
    public ResponseEntity<String> test() {
        return ResponseEntity.ok("Controlador funcionando correctamente");
    }

    @PostMapping(
    value = "/test-upload",
    consumes = MediaType.MULTIPART_FORM_DATA_VALUE
)
public ResponseEntity<String> testUpload(@RequestParam("file") MultipartFile file) {
    return ResponseEntity.ok(
        "Recibido: " + file.getOriginalFilename() + " (" + file.getSize() + " bytes)"
    );
}


    // ---------- CONTROLADOR DE AUDIO REAL ----------
   @PostMapping(
        value = "/audio-real",
        consumes = MediaType.MULTIPART_FORM_DATA_VALUE
)
public ResponseEntity<String> reconocerAudioReal(@RequestParam("file") MultipartFile file){
        try {
            if (file == null || file.isEmpty()) {
                return ResponseEntity.badRequest()
                        .body("No se recibió ningún archivo");
            }

            System.out.println("Archivo recibido correctamente:");
            System.out.println("Nombre: " + file.getOriginalFilename());
            System.out.println("Tamaño: " + file.getSize());
            System.out.println("Tipo: " + file.getContentType());

            byte[] audioData = file.getBytes();
            String resultado = audioService.reconocerAudio(audioData);

            return ResponseEntity.ok(resultado);

        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.status(500)
                    .body("Error en el servidor: " + e.getMessage());
        }
    }

    // ---------- CONTROLADOR VIDEO ----------
    @PostMapping("/video")
    public ResponseEntity<String> reconocerVideo(@RequestParam("file") MultipartFile video){
        try {
            String resultado = gestor.reconoerVideo(video.getBytes());
            return ResponseEntity.ok(resultado);
        } catch (Exception e) {
            return ResponseEntity.status(500).body("Error: " + e.getMessage());
        }
    }

    // ---------- CONTROLADOR TEXTO ----------
    @PostMapping("/texto")
    public ResponseEntity<String> reconocerTexto(@RequestParam("texto") String texto){
        try {
            String resultado = recognitionBDService.reconocerTextoEnBD(texto);
            return ResponseEntity.ok(resultado);
        } catch (Exception e) {
            return ResponseEntity.status(500).body("Error: " + e.getMessage());
        }
    }
}
