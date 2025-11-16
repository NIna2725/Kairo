package com.mediahunters.kairo.service.recognition;

import javax.crypto.Mac;
import java.util.Base64;
import javax.crypto.spec.SecretKeySpec;
import reactor.core.publisher.Mono;
import org.springframework.core.io.ByteArrayResource;
import org.springframework.http.MediaType;
import org.springframework.http.client.MultipartBodyBuilder;
import org.springframework.web.reactive.function.BodyInserters;
import org.springframework.web.reactive.function.client.WebClient;
import org.springframework.stereotype.Service;

@Service
public class AudioACRCloudService {
    
    private final String host = "identify-us-west-2.acrcloud.com";
    private final String accessKey = "8d315112b6de3ba72434e246166b53f2";
    private final String accessSecret = "Cw2wTZJOJ76WkvrK7GNFIRwA97DGWzr4v6M8erBg";

    public String reconocerAudio(byte[] audio){
        try {
            String method = "POST";
            String signatureVersion = "1";
            String timestamp = String.valueOf(System.currentTimeMillis() / 1000);
            String dataType = "audio";
            String sampleBytes = String.valueOf(audio.length);

            // Generar firma correcta
            String signature = crearFirma(
                method,
                "/v1/identify",
                accessKey,
                dataType,
                signatureVersion,
                timestamp,
                accessSecret
            );

            // Construir multipart form-data
            MultipartBodyBuilder builder = new MultipartBodyBuilder();

            // Archivo
            builder.part("sample", new ByteArrayResource(audio) {
                @Override
                public String getFilename() {
                    return "audio.mp3";
                }
            }).contentType(MediaType.APPLICATION_OCTET_STREAM);

            // Campos requeridos por ACRCloud
            builder.part("access_key", accessKey);
            builder.part("data_type", dataType);
            builder.part("signature_version", signatureVersion);
            builder.part("signature", signature);
            builder.part("timestamp", timestamp);
            builder.part("sample_bytes", sampleBytes); // <--- OBLIGATORIO EN ESTE ENDPOINT

            // Enviar request al servidor de ACRCloud
            WebClient client = WebClient.create("https://" + host);

            Mono<String> response = client.post()
                .uri("/v1/identify")
                .contentType(MediaType.MULTIPART_FORM_DATA)
                .body(BodyInserters.fromMultipartData(builder.build()))
                .retrieve()
                .bodyToMono(String.class);

            return response.block();
            
        } catch (Exception e) {
            e.printStackTrace();
            return "Error: " + e.getMessage();
        }
    }

    // Firma correcta para ACRCloud
    private String crearFirma(String method, String uri, String accessKey, 
                              String dataType, String signatureVersion, 
                              String timestamp, String secretKey) throws Exception {

        String stringToSign = method + "\n"
                + uri + "\n"
                + accessKey + "\n"
                + dataType + "\n"
                + signatureVersion + "\n"
                + timestamp;

        Mac sha1HMAC = Mac.getInstance("HmacSHA1");
        SecretKeySpec secretKeySpec = new SecretKeySpec(secretKey.getBytes("UTF-8"), "HmacSHA1");
        sha1HMAC.init(secretKeySpec);

        byte[] hash = sha1HMAC.doFinal(stringToSign.getBytes("UTF-8"));
        return Base64.getEncoder().encodeToString(hash);
    }
}
